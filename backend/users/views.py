from django.shortcuts import get_object_or_404
import jwt
from django.db import transaction

from rest_framework import viewsets, permissions, status, mixins
from rest_framework.response import Response
from rest_framework.decorators import action
from rest_framework_simplejwt.tokens import AccessToken, RefreshToken
from rest_framework_simplejwt.exceptions import TokenError, InvalidToken
from rest_framework.views import APIView
from .models import User, RolesUser
from .serializers import UserSerializer, LoginSerializer
from kidedvisor.constant import SUCCESSFUL_REGISTRATION_MESSAGE, INVALID_TOKEN_MESSAGE, FRONTEND_LOGIN_URL
from .utils import send_email_for_user_login, create_token_for_role


class UserViewSet(mixins.UpdateModelMixin,
                  mixins.DestroyModelMixin,
                  viewsets.GenericViewSet):
    """
    Представление для работы с пользователями.
    Создание, чтение, обновление и удаление пользователей.
    Дополнительная логика:
    - Присвоение ролей пользователю при регистрации.в
    - Поддерживаемые роли: 'parent', 'owner'.
    - Отправка письма на email при регистрации пользователя в системе в ролях 'parent' и 'owner'.
    Доступные HTTP методы: GET, PATCH, DELETE, POST.
    """

    queryset = User.objects.all()
    serializer_class = UserSerializer

    http_method_names = ['get', 'patch', 'delete', 'post']

    def get_permissions(self):
        """
        Ограничиваем действия для неавторизованных пользователей.
        Им доступна только регистрация в системе.
        """

        if self.action in ['register_parent', 'register_owner']:
            return [permissions.AllowAny()]
        return [permissions.IsAuthenticated()]

    @action(detail=False, methods=['post'])
    def register_parent(self, request):
        """Регистрация пользователя в системе в роли родителя ('parent')."""

        return self._register_user(request=request, role='parent')

    @action(detail=False, methods=['post'])
    def register_owner(self, request):
        """
        Регистрация пользователя в системе в роле владельца секции 'owner'.
        """

        return self._register_user(request=request, role='owner')

    @staticmethod
    @transaction.atomic
    def _register_user(request, role):
        """
        Общий статический метод для регистрации пользователя с указанной ролью.

        Если пользователь с данным email уже существует,
        проверяется наличие указанной роли.
        Если роль уже присвоена, возвращается сообщение об ошибке.
        Если роль отсутствует, она добавляется пользователю.

        Если пользователь не существует,
        создается новый пользователь с указанной ролью.

        Возвращает Response с сообщением об успешной регистрации или ошибке.
        """

        user = User.objects.filter(email=request.data['email']).first()
        
        redirect_url = request.data.get('redirect_url', '')

        if user:
            if RolesUser.objects.filter(user=user, role=role).exists():
                return Response(
                    {'message': 'Вы уже зарегистрированы в системе'},
                    status=status.HTTP_400_BAD_REQUEST,
                )

            RolesUser.objects.create(user=user, role=role)
            access_token = create_token_for_role(user=user, role=role)
            send_email_for_user_login(user, token=access_token, redirect_url=redirect_url)
            return Response(
                {'message': SUCCESSFUL_REGISTRATION_MESSAGE},
                status=status.HTTP_200_OK
            )

        serializer = UserSerializer(data=request.data, context={'request': request})
        serializer.is_valid(raise_exception=True)
        user = serializer.save_with_role(role)
        access_token = create_token_for_role(user=user, role=role)
        send_email_for_user_login(user, token=access_token, redirect_url=redirect_url)
        return Response(
            {'message': SUCCESSFUL_REGISTRATION_MESSAGE},
            status=status.HTTP_201_CREATED
        )

    @action(detail=False, methods=['get'])
    def get_me(self, request):
        """Получение информации о пользователе."""

        user = User.objects.get(id=request.user.id)
        serializer = self.get_serializer(user)
        return Response(serializer.data)
    

class ExchangeTokenView(APIView):
    """
    Класс для передачи  новых пар токенов 'access_token' и 'refresh_token' после первичной аутентификации.
    через ссылку отправленную на email.
    """

    permission_classes = (permissions.IsAuthenticated, )

    def post(self, request):
        """Метод для обмена access токена на новую пару refresh и access с заданной ролью."""

        access_token_str = request.headers.get('Authorization').split()[1]

        try:
            access_token = AccessToken(access_token_str)
            user_id = access_token['user_id']
            user = User.objects.get(id=user_id)
            role = access_token['role']

        except TokenError as exc:
            raise InvalidToken({'detail': str(exc)})
        else:
            refresh_token = RefreshToken.for_user(user)
            refresh_token['role'] = role
            new_access_token = refresh_token.access_token
            new_access_token['role'] = role
        
        return Response(
            {
                'access_token' : str(new_access_token),
                'refresh_token' : str(refresh_token)
            }, status=status.HTTP_200_OK
        )


class RefreshAccessTokenView(APIView):
    """
    Класс для обновления access токена.
    """
    def post(self, request, *args, **kwargs):
        """
        Метод для обновления access токена. При этом проверяется валидность refresh токена.
        Если валиден, то обновляется access токен. Иначе возвращается сообщение об ошибке.
        В теле запроса должен передаваться refresh токен, который был получен при первичной аутентификации.
        """

        refresh_token_str = request.data.get('refresh_token')
        if not refresh_token_str:
            return Response({
                'detail': 'Токен отсутствует.',
                'login_url': FRONTEND_LOGIN_URL,
                },
                status=status.HTTP_400_BAD_REQUEST)

        decode_refresh_token = jwt.decode(
            refresh_token_str,
            options={'verify_signature': False},
            algorithms=["HS256"]
        )
        user_id = decode_refresh_token.get('user_id')
        user = User.objects.get(id=user_id)
        role = decode_refresh_token.get('role')
        redirect_url = request.data.get('redirect_url', '')
        
        try:
            refresh_token = RefreshToken(refresh_token_str)

        except TokenError:
            access_token = create_token_for_role(user=user, role=role)
            send_email_for_user_login(
                user,
                token=access_token,
                redirect_url=redirect_url
                )
            raise InvalidToken({'detail': INVALID_TOKEN_MESSAGE})
        else:
            new_access_token = refresh_token.access_token
            new_access_token['role'] = role

            return Response(
                {
                    'access_token' : str(new_access_token),
                }, status=status.HTTP_200_OK
            )

class CustomLoginView(APIView):
    """
    Кастомный логин для аутентификации пользователя.
    """

    def post(self, request, *args, **kwargs):
        """
        Метод для аутентификации пользователя.
        Принимает на вход одно поле field после валидации в сериализаторе возвращает поле'phone_number' или 'email'.
        Отправляет на почту ссылку для получения токена первичной аутентификации.
        если в request.data передан redirect_url, то будет использован redirect_url для редиректа.
        """


        serializer = LoginSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        validated_data = serializer.validated_data

        if 'phone_number' in validated_data and 'email' in validated_data:
            return Response({'detail': 'Нельзя задать оба параметра одновременно.'}, status=status.HTTP_400_BAD_REQUEST)

        if validated_data['phone_number']:
            search_param = {'phone_number': validated_data['phone_number'] }
            default_role = 'parent'

        elif validated_data['email']:
            search_param = {'email': validated_data['email']}
            default_role = 'owner'

        else:
            return Response({'detail': 'Нет параметров для аутентификации.'}, status=status.HTTP_400_BAD_REQUEST)

        user = get_object_or_404(User, **search_param)

        if RolesUser.filter(user=user, role='moderator').exists():
            role = 'moderator'

        else:
            role = default_role

            if not RolesUser.filter(user=user, role=role).exists():
                return Response({'detail': 'У пользователя нет такой роли.'}, status=status.HTTP_403_FORBIDDEN)
        
        redirect_url = request.data.get('redirect_url', '')
        access_token = create_token_for_role(user=user, role=role)
        send_email_for_user_login(user, token=access_token, redirect_url=redirect_url)
        return Response(
            {'message': 'Письмо с ссылкой для входа отправлено на вашу почту.'},
            status=status.HTTP_200_OK
        )
    
class ChangeUserRoleView(APIView):
    """ API для смены роли пользователя. Используется метод PUT для замены роли."""

    permission_classes = [permissions.IsAuthenticated]

    def put(self, request, *args, **kwargs):
        """Метод для смены роли пользователя."""
        
    # Access Token уже проверен через permissions.IsAuthenticated
        auth_header = request.headers.get('Authorization')
        access_token_str = auth_header.split()[1]
        try:
            # Декодируем Access Token
            access_token = AccessToken(access_token_str)
            current_role = access_token.get('role')
        except Exception as exc:
            return Response({"detail": f"Ошибка валидации токена: {str(exc)}"}, status=status.HTTP_400_BAD_REQUEST)

        # Получаем пользователя
        user = request.user

        # Получаем список всех ролей пользователя
        check_rolle_user = list(RolesUser.objects.filter(user=user).values_list('role', flat=True))

        if current_role in check_rolle_user:
            check_rolle_user.remove(current_role)  # Удаляем текущую роль

            # Проверяем, что у пользователя осталась хотя бы одна роль
            if len(check_rolle_user) != 1:
                return Response({'detail': 'У пользователя нет доступных ролей для смены.'}, status=status.HTTP_400_BAD_REQUEST)

            role = check_rolle_user[0]  # Выбираем единственную оставшуюся роль

            # Проверяем наличие refresh_token в теле запроса
            refresh_token = request.data.get('refresh_token')
            if not refresh_token:
                return Response({'detail': 'Refresh token отсутствует.'}, status=status.HTTP_400_BAD_REQUEST)

            try:
                # Отзываем старый Refresh Token
                token = RefreshToken(refresh_token)
                token.blacklist()
            except Exception as exc:
                return Response({'detail': f'Ошибка при отзыве токена: {str(exc)}'}, status=status.HTTP_400_BAD_REQUEST)

            # Генерация новой пары токенов (Access и Refresh)
            new_refresh_token = RefreshToken.for_user(user)
            new_refresh_token['role'] = role  # Добавляем новую роль в Refresh Token
            new_access_token = new_refresh_token.access_token
            new_access_token['role'] = role  # Добавляем новую роль в Access Token

            # Возвращаем новые токены
            return Response({
                'message': 'Роль успешно изменена.',
                'access_token': str(new_access_token),
                'refresh_token': str(new_refresh_token)
            }, status=status.HTTP_200_OK)

        # Если текущая роль не найдена в списке ролей пользователя
        return Response({'detail': 'Текущая роль не найдена у пользователя.'}, status=status.HTTP_403_FORBIDDEN)
                

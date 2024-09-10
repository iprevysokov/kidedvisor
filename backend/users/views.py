from django.db import transaction
from rest_framework import viewsets, permissions, status, mixins
from rest_framework.response import Response
from rest_framework.decorators import action
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView
from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework_simplejwt.exceptions import TokenError, InvalidToken
from rest_framework.views import APIView
from .models import User, RolesUser
from .serializers import UserSerializer, UserTokenObtainPairSerializer
from kidedvisor.constant import SUCCESSFUL_REGISTRATION_MESSAGE
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

        if user:
            if RolesUser.objects.filter(user=user, role=role).exists():
                return Response(
                    {'message': 'Вы уже зарегистрированы в системе'},
                    status=status.HTTP_400_BAD_REQUEST,
                )

            RolesUser.objects.create(user=user, role=role)
            refresh, access = create_token_for_role(user=user, role=role)
            send_email_for_user_login(user, message=f"{refresh}, {access}")
            return Response(
                {'message': SUCCESSFUL_REGISTRATION_MESSAGE},
                status=status.HTTP_200_OK
            )

        serializer = UserSerializer(data=request.data, context={'request': request})
        serializer.is_valid(raise_exception=True)
        user = serializer.save_with_role(role)
        tokens = create_token_for_role(user=user, role=role)
        refresh = tokens['refresh']
        access = tokens['access']
        send_email_for_user_login(user, message=f"{refresh}, {access}")
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


class RefreshAccessTokenView(APIView):
    def post(self, request, *args, **kwargs):
        refresh_token = request.data.get("refresh_token")
        if refresh_token:
            try:
                refresh = RefreshToken(refresh_token)
                new_access_token = refresh.access_token
                tokens = {'access': str(new_access_token), }
                return Response(tokens, status=status.HTTP_200_OK)
            except TokenError as e:
                raise InvalidToken({"detail": "Invalid refresh token"})
            except InvalidToken as e:
                return Response({"error": str(e)}, status=status.HTTP_400_BAD_REQUEST)
        return Response({'error': 'Refresh token not provided'}, status=status.HTTP_400_BAD_REQUEST)

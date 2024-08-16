from rest_framework import viewsets, permissions, status
from rest_framework.response import Response
from rest_framework.decorators import action

from users.models import User, RolesUser
from .serializers import UserSerializer


class UserViewSet(viewsets.ModelViewSet):
    """
    Представление для  работы с пользователя.
    Создание, чтение, обновление, удаление
    Назначение ролеи пользователю (родитель, владелец).
    """

    queryset = User.objects.all()
    serializer_class = UserSerializer

    def get_permissions(self):
        """
        Для не авторизованного пользователя.
        Ограничиваем действия только регистрацией в системе.
        """

        if self.action in ['register_parent', 'register_owner']:
            return [permissions.AllowAny()]
        return [permissions.IsAuthenticated()]

    def get_queryset(self):
        """Запрещаем получение списка пользователей. Всем пользователям."""

        if self.action == 'list':
            return User.objects.none()
        return super().get_queryset()

    def create(self, request, *args, **kwargs):
        """Запрещаем создание пользователя через стандартные маршруты."""

        return Response(
            {
                'message':
                'Регистрация доступна только через специальные маршруты.'
            },
            status=status.HTTP_405_METHOD_NOT_ALLOWED,
        )

    @action(detail=False, methods=['post'])
    def register_parent(self, request):
        """Регистрация родителя."""

        return self._register_user(request, 'parent')

    @action(detail=False, methods=['post'])
    def register_owner(self, request):
        """Регистрация владельца."""

        return self._register_user(request, 'owner')

    @staticmethod
    def _register_user(request, role):
        """Общий метод регистрации пользователя."""

        text = 'Вы успешно зарегистрировались. Вам направлено письмо на email'

        user = User.objects.filter(email=request.data['email']).first()

        if user:
            if RolesUser.objects.filter(user=user, role=role).exists():
                return Response(
                    {'message': 'Вы уже зарегистрированы в системе'},
                    status=status.HTTP_400_BAD_REQUEST,
                )

            RolesUser.objects.create(user=user, role=role)
            return Response({'message': text}, status=status.HTTP_200_OK)

        serializer = UserSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save_with_role(role)
        return Response({'message': text}, status=status.HTTP_201_CREATED)

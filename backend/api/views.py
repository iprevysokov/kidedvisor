from rest_framework import viewsets, permissions, status, mixins
from rest_framework.response import Response
from rest_framework.decorators import action

from users.models import User, RolesUser
from .serializers import UserSerializer


class UserViewSet(mixins.UpdateModelMixin,
                  mixins.DestroyModelMixin,
                  viewsets.GenericViewSet):
    """
    Представление для  работы с пользователями.
    Создание, чтение, обновление, удаление
    Доополнительная логика:
    Присвоечения ролей пользователю при регистрации.
    Роли: 'parent', 'owner'.
    Доступные HTTP методы: GET, PATCH, DELETE, POST

    """

    queryset = User.objects.all()
    serializer_class = UserSerializer

    http_method_names = ['get', 'patch', 'delete', 'post']

    def get_permissions(self):
        """
        Для любого не авторизованного пользователя,
        ограничиваем действия. Им доступна только регистрацией в системе.
        """

        if self.action in ['register_parent', 'register_owner']:
            return [permissions.AllowAny()]
        return [permissions.IsAuthenticated()]

    @action(detail=False, methods=['post'])
    def register_parent(self, request):
        """Регистраци пользователя в системе в роле родителя 'parent'."""

        return self._register_user(request=request, role='parent')

    @action(detail=False, methods=['post'])
    def register_owner(self, request):
        """
        Регистраци пользователя в системе в роле владельца секции 'owner'.
        """

        return self._register_user(request=request, role='owner')

    @staticmethod
    def _register_user(request, role):
        """
        Общий статический метод регистрации пользователя, для всех ролей.
        При попытке зарегистрировать существующего пользователя в системе,
        выполняется проверка роли пользователя.
        Если такой роли у пользователя нет, то присваивается новая роль.
        """

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

    @action(detail=False, methods=['get'])
    def get_me(self, request):
        """Получение информации о пользователе."""

        user = User.objects.get(id=request.user.id)
        serializer = self.get_serializer(user)
        return Response(serializer.data)

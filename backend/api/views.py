from rest_framework import viewsets

from users.models import User
from .serializers import UserSerializer


class UserViewSet(viewsets.ModelViewSet):
    """
    Представление для  работы с пользователя.
    Создание, чтение, обновление, удаление
    Назначение ролеи пользователю.
    """

    queryset = User.objects.all()
    serializer_class = UserSerializer

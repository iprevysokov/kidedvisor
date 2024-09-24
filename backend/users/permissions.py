from rest_framework.permissions import BasePermission
from rest_framework_simplejwt.authentication import JWTAuthentication
from rest_framework_simplejwt.exceptions import InvalidToken, TokenError


def check_user_role(role, request):
    """Функция для проверки аутентификации и роли пользователя."""

    # Проверка на аутентификацию через JWT
    auth = JWTAuthentication()

    try:
        user_token, _ = auth.authenticate(request)
        role_in_token = user_token.payload.get('role', None)
        return role_in_token == role

    except (InvalidToken, TokenError):
        return False


class IsAuthenticatedParent(BasePermission):
    """Класс для проверки аутентификации и доступа в роли Родителя."""

    def has_permission(self, request, view):

        role = 'parent'
        return check_user_role(role, request)


class IsAuthenticatedOwner(BasePermission):
    """Класс для проверки аутентификации и доступа в роли Владельца секции."""

    def has_permission(self, request, view):

        role = 'owner'
        return check_user_role(role, request)


class IsAuthenticatedModerator(BasePermission):
    """Класс для проверки аутентификации и доступа в роли Модератора."""

    def has_permission(self, request, view):

        role = 'moderator'
        return check_user_role(role, request)

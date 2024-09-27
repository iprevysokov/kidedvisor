from rest_framework.permissions import BasePermission
from rest_framework_simplejwt.authentication import JWTAuthentication
from rest_framework_simplejwt.exceptions import InvalidToken, TokenError


class IsAuthenticatedWithRole(BasePermission):
    """Базовый класс для проверки аутентификации и роли пользователя."""

    role = None  # Роль, которую нужно проверять

    def has_permission(self, request, view):

        if self.role:
            return self.check_user_role(request)
        return False

    def check_user_role(self, request):
        """Метод для проверки аутентификации и роли пользователя."""

        auth = JWTAuthentication()

        try:
            user_token, _ = auth.authenticate(request)
            role_in_token = user_token.payload.get('role', None)
            return role_in_token == self.role

        except (InvalidToken, TokenError):
            return False


class IsAuthenticatedParent(IsAuthenticatedWithRole):
    """Пермишн для проверки аутентификации и роли родителя."""
    role = 'parent'


class IsAuthenticatedOwner(IsAuthenticatedWithRole):
    """Пермишн для проверки аутентификации и роли владельца секции."""
    role = 'owner'


class IsAuthenticatedModerator(IsAuthenticatedWithRole):
    """Пермишн для проверки аутентификации и роли модератора."""
    role = 'moderator'

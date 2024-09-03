from django.conf import settings
from django.core.mail import send_mail
from email.header import Header

from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework_simplejwt.exceptions import TokenError, InvalidToken

from kidedvisor.constant import TEXT_ENTER_APP
from users.models import User, RolesUser


def send_email_for_user_login(
        user, message='url'
):
    """
    Отправка письма пользователю.

    :param user: Экземпляр пользователя, которому отправляется письмо.
    :param message: Сообщение для входа в систему (передаем ссылку для аутентификации.)
    """
    send_mail(
        subject=Header(TEXT_ENTER_APP, 'utf-8'),
        message=message,
        recipient_list=[user.email],
        from_email=settings.DEFAULT_FROM_EMAIL
    )


def create_token_from_user(user):
    refresh = RefreshToken.for_user(user)
    role = RolesUser.objects.get(user=user).role
    refresh['role'] = role
    return {
        'refresh': str(refresh),
        'access': str(refresh.access_token),
    }


def revoke_check(refresh, user):
    try:
        refresh = RefreshToken(refresh)
        token_role = refresh.get('role')
        if token_role != RolesUser.object.get(user=user).role:
            refresh.blacklist()
            raise InvalidToken({"detail": "Role mismatch. Token has been revoked."})
        new_access_token = refresh.access_token
        return {'access': str(new_access_token),}
    except TokenError as e:
        raise InvalidToken({"detail": "Invalid refresh token"})

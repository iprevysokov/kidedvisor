from django.conf import settings
from django.core.mail import send_mail
from email.header import Header

from rest_framework_simplejwt.tokens import RefreshToken
from rest_framework_simplejwt.exceptions import TokenError, InvalidToken

from kidedvisor.constant import TEXT_ENTER_APP, FRONTEND_ACCESS_URL

def send_email_for_user_login(
        user, frontend_access_url=FRONTEND_ACCESS_URL, token='token', redirect_url='url_2'
):
    """
    Отправка письма пользователю.

    :param user: Экземпляр пользователя, которому отправляется письмо.
    :param message: Сообщение для входа в систему (передаем ссылку для аутентификации.)
    """

    message = f'{frontend_access_url}?access_token={token}&redirect_url={redirect_url}'
    send_mail(
        subject=Header(TEXT_ENTER_APP, 'utf-8'),
        message=message,
        recipient_list=[user.email],
        from_email=settings.DEFAULT_FROM_EMAIL
    )


def create_token_for_role(user, role):
    
    refresh = RefreshToken.for_user(user)
    access_token = refresh.access_token
    access_token['role'] = role  # Добавляем роль в access токен

    return str(access_token)


def revoke_and_create_new_token(refresh_token, user, new_role):
    try:
        refresh = RefreshToken(refresh_token)
        token_role = refresh.get('role')
        if token_role != new_role:
            refresh.blacklist()

            new_refresh = RefreshToken.for_user(user)
            new_refresh['role'] = new_role

            return {
                'refresh': str(new_refresh),
                'access': str(new_refresh.access_token),
            }
        else:
            new_access_token = refresh.access_token
            return {
                'access': str(new_access_token),
            }
    except TokenError:
        raise InvalidToken({'detail': 'Invalid refresh token'})

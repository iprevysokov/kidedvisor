from datetime import timedelta
from django.conf import settings
from django.core.mail import send_mail
from email.header import Header

from rest_framework_simplejwt.tokens import RefreshToken

from kidedvisor.constant import (TEXT_ENTER_APP, FRONTEND_ACCESS_URL,
                                 TOKEN_LIFETIME_MIN)


def send_email_for_user_login(
        user,
        frontend_access_url=FRONTEND_ACCESS_URL,
        token='token',
        redirect_url='url_2'
):
    """
    Отправка письма пользователю.
    (передаем ссылку для аутентификации.)

    :param user: Экземпляр пользователя, которому отправляется письмо.
    :param message: Сообщение для входа в систему
    :param token: Токен для входа в систему
    :param redirect_url: Ссылка для перенаправления после входа
    """

    message = f'''
{frontend_access_url}?access_token={token}&redirect_url={redirect_url}
'''
    send_mail(
        subject=Header(TEXT_ENTER_APP, 'utf-8'),
        message=message,
        recipient_list=[user.email],
        from_email=settings.DEFAULT_FROM_EMAIL
    )


def create_token_for_role(user, role):
    """
    Создание access токена с заданной ролью. Для первичной аутентификации.
    Продолжительность существования токена задается в конфигурации.
    kidedvisor.constant.TOKEN_LIFETIME_MIN
    """

    refresh = RefreshToken.for_user(user)

    # Устанавливаем время жизни первичного access токена
    refresh.set_exp(lifetime=timedelta(minutes=TOKEN_LIFETIME_MIN))
    access_token = refresh.access_token

    # Добавляем роль в access токен
    access_token['role'] = role

    # Устанавливаем время жизни первичного access токена
    access_token.set_exp(lifetime=timedelta(minutes=TOKEN_LIFETIME_MIN))

    return str(access_token)

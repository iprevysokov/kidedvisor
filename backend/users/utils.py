from datetime import timedelta
from django.conf import settings
from django.core.mail import send_mail
from email.header import Header

from rest_framework_simplejwt.tokens import RefreshToken

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
    access_token.set_exp(lifetime=timedelta(minutes=10)) # Устанавливаем время жизни первичного access токена

    return str(access_token)

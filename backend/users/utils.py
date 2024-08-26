
from django.conf import settings
from django.core.mail import send_mail
from email.header import Header

from kidedvisor.constant import TEXT_ENTER_APP


def send_email_for_user_login(
        user, message='url'
        ):
    """
    Отправка письма пользователю.

    :param user: Экземпляр пользователя, которому отправляется письмо.
    :param message: Сообщение для входа в систему (передаем ссылку для входа.)
    """
    send_mail(
        subject=Header(TEXT_ENTER_APP, 'utf-8'),
        message=message,
        recipient_list=[user.email],
        from_email=settings.DEFAULT_FROM_EMAIL
    )

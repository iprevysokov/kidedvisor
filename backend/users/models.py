from django.contrib.auth.models import AbstractUser, BaseUserManager # noqa
from django.db import models # noqa
from phonenumber_field.modelfields import PhoneNumberField # noqa

from kidedvisor.constant import MAX_LENGTH_EMAIL_FIELD, MAX_LENGTH_CHAR_FIELD # noqa


class User(AbstractUser):
    """Базовая модель пользователя."""

    pass

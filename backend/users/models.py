from django.contrib.auth.models import AbstractUser
from django.db import models
from phonenumber_field.modelfields import PhoneNumberField

from kidedvisor.constant import (MAX_LENGTH_EMAIL_FIELD, MAX_LENGTH_CHAR_FIELD,
                                 USERNAME)


class User(AbstractUser):
    """Модель пользователя."""

    USERNAME_FIELD = USERNAME

    REQUIRED_FIELDS = ['first_name', 'last_name', 'email', 'phone_number']

    username = None

    phone_number = PhoneNumberField(
        'Номер телефона',
        unique=True,
        blank=False,
        null=False,
    )

    email = models.EmailField(
        'Электронная почта',
        max_length=MAX_LENGTH_EMAIL_FIELD,
        unique=True
        )

    first_name = models.CharField(
        'Имя',
        max_length=MAX_LENGTH_CHAR_FIELD,
    )

    last_name = models.CharField(
        'Фамилия',
        max_length=MAX_LENGTH_CHAR_FIELD,
    )

    class Meta:
        verbose_name = 'Пользователь'
        verbose_name_plural = 'Пользователи'
        ordering = ('id',)

    def __str__(self):
        return (
            f'phone_number: {self.phone_number}, '
            f'Email: {self.email}'
        )

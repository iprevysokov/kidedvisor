from django.contrib.auth.models import AbstractUser, BaseUserManager
from django.db import models
from phonenumber_field.modelfields import PhoneNumberField

from kidedvisor.constant import MAX_LENGTH_EMAIL_FIELD, MAX_LENGTH_CHAR_FIELD


class UserManager(BaseUserManager):
    """Менеджер пользователя."""

    def create_user(
            self, email, phone_number=None, password=None, **extra_fields
            ):
        """Создание пользователя."""

        if not email:
            raise ValueError('Email обязательное поле.')
        if extra_fields.get('is_superuser') is not True and not phone_number:
            raise ValueError('Номер телефона обязательное поле.')
        user = self.model(
            email=self.normalize_email(email),
            phone_number=phone_number,
            **extra_fields
        )
        if password:
            user.set_password(password)
        else:
            user.set_unusable_password()
        user.save(using=self._db)
        return user

    def create_superuser(self, email, password, **extra_fields):
        """Создание суперпользователя."""

        extra_fields.setdefault('is_staff', True)
        extra_fields.setdefault('is_superuser', True)
        user = self.create_user(email, password=password, **extra_fields)
        user.save(using=self._db)
        return user


class User(AbstractUser):
    """Базовая модель пользователя."""

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = []

    username = None

    email = models.EmailField(
        'Электронная почта',
        max_length=MAX_LENGTH_EMAIL_FIELD,
        unique=True,
        blank=False,
        null=False,
        )

    phone_number = PhoneNumberField(
        'Номер телефона',
        unique=True,
        blank=True,
        null=True,
    )

    first_name = models.CharField(
        'Имя',
        max_length=MAX_LENGTH_CHAR_FIELD,
        blank=True,
    )

    last_name = models.CharField(
        'Фамилия',
        max_length=MAX_LENGTH_CHAR_FIELD,
        blank=True,
    )

    objects = UserManager()

    class Meta:
        verbose_name = 'Пользователь'
        verbose_name_plural = 'Пользователи'
        ordering = ('id',)

    def __str__(self):
        return (
            f'Создан пользователь {self.email})'
        )


class Parents(models.Model):
    """Модель родителя."""

    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,
        primary_key=True,
        related_name='parent'
    )

    def __str__(self):
        return f'Родитель: {self.user.email}'


class SectionsOwner(models.Model):
    """Модель владельца секции."""

    user = models.OneToOneField(
        User,
        on_delete=models.CASCADE,
        primary_key=True,
        related_name='section_owner'
        )

    def __str__(self):
        return f'Владелец секции: {self.user.email}'

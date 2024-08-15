from django.contrib.auth.models import AbstractUser, BaseUserManager
from django.db import models
from django.utils import timezone
from phonenumber_field.modelfields import PhoneNumberField

from kidedvisor.constant import MAX_LENGTH_EMAIL_FIELD, MAX_LENGTH_CHAR_FIELD


class UserManager(BaseUserManager):
    """Менеджер пользователя."""

    def create_user(
            self, email, phone_number, password=None, **extra_fields
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
        user = self.create_user(
            email, phone_number=None, password=password, **extra_fields
            )

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
            f'Создан пользователь {self.email}'
        )


class RolesUser(models.Model):
    """Модель ролей пользователя."""

    ROLE_CHOICES = (
        ('parent', 'Parent'),
        ('owner', 'Section Owner'),
    )

    user = models.ForeignKey(
        User,
        on_delete=models.CASCADE,
        related_name='roles',
        verbose_name='Пользователь',
    )

    role = models.CharField(
        'Роль',
        max_length=MAX_LENGTH_CHAR_FIELD,
        blank=False,
        null=False,
        choices=ROLE_CHOICES,
    )
    date_joined = models.DateTimeField(
        'Дата получения роли',
        default=timezone.now,
    )

    class Meta:
        verbose_name = 'Роль пользователя'
        verbose_name_plural = 'Роли пользователей'
        ordering = ('user',)
        constraints = [
            models.UniqueConstraint(
                fields=['user', 'role'],
                name='unique_user_role'
                )
        ]

    def __str__(self):
        return f'Пользователь: {self.user} получил роль: {self.role}'

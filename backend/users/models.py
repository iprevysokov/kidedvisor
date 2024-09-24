import os
from django.contrib.auth.models import AbstractUser, BaseUserManager
from django.db import models
from django.utils import timezone
from phonenumber_field.modelfields import PhoneNumberField
from better_profanity import profanity
from kidedvisor.constant import MAX_LENGTH_EMAIL_FIELD, MAX_LENGTH_CHAR_FIELD


# Функция для цензуры текста
def censor_text(text):
    profanity.load_censor_words()  # Загружаем стандартный список слов
    return profanity.censor(text)


class UserManager(BaseUserManager):
    """Переопределенный, пользовательский менеджер."""

    def create_user(self, email, phone_number, password=None, **extra_fields):
        """
        Создание пользователя.

        - Номер телефона обязателен для любого пользователя,
        кроме суперпользователя.
        - Пароль не обязателен для любого пользователя,
        кроме суперпользователя.
        - Устанавливается неактивный пароль (set_unusable_password) для любого
        пользователя, кроме суперпользователя.
        - Для суперпользователя пароль является обязательным полем.
        """

        if not email:
            raise ValueError("Email обязательное поле.")
        if extra_fields.get("is_superuser") is not True and not phone_number:
            raise ValueError("Номер телефона обязательное поле.")
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
        """
        Создание суперпользователя.

        - Пароль является обязательным полем.
        - Номер телефона не обязателен для суперпользователя.
        """

        extra_fields.setdefault("is_staff", True)
        extra_fields.setdefault("is_superuser", True)
        user = self.create_user(
            email, phone_number=None, password=password, **extra_fields
        )

        return user


class User(AbstractUser):
    """Переопределенная модель пользователя."""

    USERNAME_FIELD = "email"
    REQUIRED_FIELDS = []

    STATUS_CHOICES = [
        ("active", "Активен"),
        ("blocked", "Пользователь заблокирован")
        ]

    username = None

    image = models.ImageField(
        "Аватарка",
        upload_to="avatars/",
        blank=True,
        null=True,
    )

    email = models.EmailField(
        "Электронная почта",
        max_length=MAX_LENGTH_EMAIL_FIELD,
        unique=True,
        blank=False,
        null=False,
    )

    phone_number = PhoneNumberField(
        "Номер телефона",
        unique=True,
        blank=True,
        null=True,
    )

    first_name = models.CharField(
        "Имя",
        max_length=MAX_LENGTH_CHAR_FIELD,
        blank=True,
        null=True,
    )

    middle_name = models.CharField(
        "Отчество",
        max_length=MAX_LENGTH_CHAR_FIELD,
        blank=True,
        null=True,
    )

    last_name = models.CharField(
        "Фамилия",
        max_length=MAX_LENGTH_CHAR_FIELD,
        blank=True,
        null=True,
    )

    status = models.CharField(
        max_length=20,
        choices=STATUS_CHOICES,
        default="active",
        verbose_name="Статус"
    )

    objects = UserManager()

    class Meta:
        verbose_name = "Пользователь"
        verbose_name_plural = "Пользователи"
        ordering = ("id",)

    def __str__(self):
        return f"Пользователь: {self.email}"

    def save(self, *args, **kwargs):
        """
        Расширение метода сохранения.
        Проверяем есть ли аватарка у пользователя.
        Если она есть, то при добавлении новой аватарки удаляем старую.
        Также применяем цензуру к текстовым полям.
        """
        # Цензура текстовых полей перед сохранением
        if self.first_name:
            self.first_name = censor_text(self.first_name)
        if self.middle_name:
            self.middle_name = censor_text(self.middle_name)
        if self.last_name:
            self.last_name = censor_text(self.last_name)

        old_image = None

        if self.pk:
            old_image = User.objects.get(pk=self.pk).image

        super().save(*args, **kwargs)

        # Удаление старого изображения, если добавлено новое
        if old_image and old_image != self.image:
            if os.path.isfile(old_image.path):
                os.remove(old_image.path)


class RolesUser(models.Model):
    """Модель ролей пользователя."""

    ROLE_CHOICES = (
        ('parent', 'Роль родителя'),
        ('owner', 'Владелец секции'),
        ('moderator', 'Модератор'),
    )

    user = models.ForeignKey(
        User,
        on_delete=models.CASCADE,
        related_name="roles",
        verbose_name="Пользователь",
    )

    role = models.CharField(
        "Роль",
        max_length=MAX_LENGTH_CHAR_FIELD,
        blank=False,
        null=False,
        choices=ROLE_CHOICES,
    )
    date_joined = models.DateTimeField(
        "Дата получения роли",
        default=timezone.now,
    )

    class Meta:
        verbose_name = "Роль пользователя"
        verbose_name_plural = "Роли пользователей"
        ordering = ("user",)
        constraints = [
            models.UniqueConstraint(
                fields=["user", "role"],
                name="unique_user_role"
            )
        ]

    def __str__(self):
        return f"Пользователь: {self.user.email} получил роль: {self.role}"

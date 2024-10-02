from decimal import Decimal
from django.db import models
from phonenumber_field.modelfields import PhoneNumberField
from django.core.validators import MaxValueValidator, MinValueValidator
from kidedvisor.constant import MAX_LENGTH_EMAIL_FIELD

# """Модель абонемента"""
# S_type = (
#    ('0', 'Индивидуальный'),
#    ('1', 'Групповой'),
# )

"""Сортировка фото"""
CHOICES = (
    ("Main", "Главное"),
    ("1", "1"),
    ("2", "2"),
    ("3", "3"),
    ("4", "4"),
)


class Category(models.Model):
    """Направления, к которым относятся секции"""

    category_name = models.CharField(
        max_length=150, db_index=True, verbose_name="Направление"
    )

    class Meta:
        ordering = ("category_name",)
        verbose_name = "Направление"
        verbose_name_plural = "Направления"

    def __str__(self):
        return self.category_name


class Cities(models.Model):
    """Города"""

    city = models.CharField(
        max_length=50, db_index=True, verbose_name="Город"
    )

    class Meta:
        # ordering = ("city",)
        verbose_name = "Город"
        verbose_name_plural = "Города"

    def __str__(self):
        return self.city


#    """Модель абонемента"""
# class subscription_type(models.Model):
#    subscription_type = models.CharField(max_length=15)

#    """Модель абонемента"""
# class Subscription(models.Model):
#    """Абонементы"""
#    subscription_name = models.CharField(max_length=150, db_index=True,
# verbose_name='Название абонемента', blank=True, null=True)
#    subscription_type = models.CharField(max_length=1, choices=S_type)
#    subscription_price = models.DecimalField(max_digits=6, decimal_places=0,
# verbose_name='Стоимость')
#    subscription_quantity = models.DecimalField(max_digits=2,
# decimal_places=0,
#  verbose_name='Количество занятий в абонементе')

#    class Meta:
#        ordering = ('subscription_name',)
#        verbose_name = 'Абонемент'
#        verbose_name_plural = 'Абонементы'

#    def __str__(self):
#        return self.subscription_name


class Section(models.Model):
    """Модель описания секции"""

    category_name = models.ForeignKey(
        Category, on_delete=models.CASCADE, verbose_name="Выберите направление"
    )
    type_name = models.CharField(
        max_length=200,
        db_index=True,
        verbose_name="Выберите вид деятельности",
        blank=True,
    )
    name = models.CharField(
        max_length=200, db_index=True, verbose_name="Название секции"
    )
    city = models.ForeignKey(
        Cities, on_delete=models.CASCADE, verbose_name="Выберите город"
    )
    address = models.CharField(
        max_length=200, db_index=True, verbose_name="Адрес"
    )
    age_s = models.DecimalField(
        max_digits=2,
        decimal_places=0,
        verbose_name="Возраст от",
        validators=[MaxValueValidator(Decimal("99")),
                    MinValueValidator(Decimal("0"))],
    )
    age_f = models.DecimalField(
        max_digits=2,
        decimal_places=0,
        verbose_name="Возраст до",
        validators=[MaxValueValidator(Decimal("99")),
                    MinValueValidator(Decimal("0"))],
    )
    phone_number = PhoneNumberField(
        "Номер телефона для связи", blank=True, null=True
    )
    email = models.EmailField(
        "Электронная почта",
        max_length=MAX_LENGTH_EMAIL_FIELD,
    )
    tg_contact = models.CharField(
        max_length=100, db_index=True, verbose_name="Телеграм", blank=True
    )
    whatsapp = PhoneNumberField(
        "Номер телефона для связи в whatsapp", blank=True, null=True
    )
    time_s = models.TimeField(
        verbose_name="Время работы с", blank=True, null=True
    )
    time_f = models.TimeField(
        verbose_name="Время работы по", blank=True, null=True
    )
    work_day_mon = models.BooleanField(verbose_name="Пн", blank=True)
    work_day_tue = models.BooleanField(verbose_name="Вт", blank=True)
    work_day_wed = models.BooleanField(verbose_name="Ср", blank=True)
    work_day_thu = models.BooleanField(verbose_name="Чт", blank=True)
    work_day_fri = models.BooleanField(verbose_name="Пт", blank=True)
    work_day_sat = models.BooleanField(verbose_name="Сб", blank=True)
    work_day_sun = models.BooleanField(verbose_name="Вс", blank=True)

    subscription = models.CharField(
        max_length=500, db_index=True, verbose_name="Абонементы", blank=True
    )
    # models.ForeignKey(Subscription, on_delete=models.CASCADE,
    # verbose_name='Выберите абонемент')
    schedule = models.CharField(
        max_length=1000, db_index=True, verbose_name="Расписание", blank=True
    )
    description = models.CharField(
        max_length=1000, db_index=True, verbose_name="Описание", blank=True
    )

    class Meta:
        ordering = ("name",)
        verbose_name = "Секция"
        verbose_name_plural = "Секции"

    def __str__(self):
        return self.name


class SectionImage(models.Model):
    section_image = models.ForeignKey(
        Section,
        default=None,
        on_delete=models.CASCADE,
        related_name='images',
        verbose_name="Секция"
    )
    images = models.ImageField(
        upload_to="images/",
        verbose_name="Фотография",
    )
    order = models.CharField(
        max_length=100,
        blank=True,
        choices=CHOICES,
        unique=True,
        verbose_name="Порядок фото",
    )

    class Meta:

        constraints = [
            models.UniqueConstraint(
                fields=['section_image', 'order'],
                name='unique_image',
            ),]

        verbose_name = "Изображение секции"
        verbose_name_plural = "Изображения секций"

    def __str__(self):
        return self.section_image.name

    def delete(self, *args, **kwargs):
        # Удаляем файл с диска перед удалением записи
        self.images.delete(save=False)
        super().delete(*args, **kwargs)

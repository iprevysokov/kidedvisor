from decimal import Decimal
from django.db import models
from django.urls import reverse
from phonenumber_field.modelfields import PhoneNumberField
from django.core.validators import MaxValueValidator, MinValueValidator
from kidedvisor.constant import MAX_LENGTH_EMAIL_FIELD, MAX_LENGTH_CHAR_FIELD


DAYS_OF_WEEK = (
    ('0', 'Понедельник'),
    ('1', 'Вторник'),
    ('2', 'Среда'),
    ('3', 'Четверг'),
    ('4', 'Пятница'),
    ('5', 'Суббота'),
    ('6', 'Воскресенье'),
)

#    """Модель абонемента"""
#S_type = (
#    ('0', 'Индивидуальный'),
#    ('1', 'Групповой'),
#)

class SectionManager():
   
    def create_section(
            self,
            name,
            phone_number,
            description,
            **extra_fields
            ):
        """
        Создание секции
        """

        if not name:
            raise ValueError('Наименование секции обязательное поле.')
        #if not phone_number:
        #    raise ValueError('Номер телефона обязательное поле.')
        #if not description:
        #    raise ValueError('Описание обязательное поле.')
        Section = self.model(
            name=name,
        #    phone_number=phone_number,
        #    description=description,
        #    **extra_fields
        )
        Section.save(using=self._db)
        return Section


class Category(models.Model):
    """Направления, к которым относятся секции"""
    category_name = models.CharField(max_length=150, db_index=True, verbose_name='Направление', blank=True, null=True)
#    type_name = models.CharField(max_length=150, db_index=True, verbose_name='Вид деятельности')

    class Meta:
        ordering = ('category_name',)
        verbose_name = 'Направление'
        verbose_name_plural = 'Направления'

    def __str__(self):
        return self.category_name
    
class Days(models.Model):
    day = models.CharField(max_length=8)

#    """Модель абонемента"""
#class subscription_type(models.Model):
#    subscription_type = models.CharField(max_length=15)

#    """Модель абонемента"""
#class Subscription(models.Model):
#    """Абонементы"""
#    subscription_name = models.CharField(max_length=150, db_index=True, verbose_name='Название абонемента', blank=True, null=True)
#    subscription_type = models.CharField(max_length=1, choices=S_type)
#    subscription_price = models.DecimalField(max_digits=6, decimal_places=0, verbose_name='Стоимость')
#    subscription_quantity = models.DecimalField(max_digits=2, decimal_places=0, verbose_name='Количество занятий в абонементе')

#    class Meta:
#        ordering = ('subscription_name',)
#        verbose_name = 'Абонемент'
#        verbose_name_plural = 'Абонементы'

#    def __str__(self):
#        return self.subscription_name
    
class Days(models.Model):
    day = models.CharField(max_length=8)
    
#class Type(models.Model):
#    """Виды деятельности, к которым относятся секции"""
#    type_name = models.CharField(max_length=150, db_index=True, verbose_name='Вид деятельности', blank=True, null=True)
#    category_name = models.ForeignKey(Category, on_delete=models.CASCADE,
#                                 verbose_name='Выберите направление')
    

#    class Meta:
#        ordering = ('type_name',)
#        verbose_name = 'Вид деятельности'
#        verbose_name_plural = 'Виды деятельности'

#    def __str__(self, cate  ):
#        return self.type_name
    
class Section(models.Model):
    """Модель описания секции"""
    category_name = models.ForeignKey(Category, on_delete=models.CASCADE,
                                 verbose_name='Выберите направление')
    type_name = models.CharField(max_length=200, db_index=True, verbose_name='Выберите вид деятельности', blank=True, null=True)
    #models.ForeignKey(Type, on_delete=models.CASCADE,                                  verbose_name='Выберите вид деятельности')
    name = models.CharField(max_length=200, db_index=True, verbose_name='Название секции')
    address = models.CharField(max_length=200, db_index=True, verbose_name='Адрес')
    age_s = models.DecimalField(max_digits=2, decimal_places=0, verbose_name='Возраст от', 
                                validators = [MaxValueValidator(99), MinValueValidator(0)])
    age_f = models.DecimalField(max_digits=2, decimal_places=0, verbose_name='Возраст до',
                                validators = [MaxValueValidator(99), MinValueValidator(0)])
    phone_number = PhoneNumberField('Контакты',unique=True, blank=True, null=True)
    email = models.EmailField('Электронная почта', max_length=MAX_LENGTH_EMAIL_FIELD, unique=True, blank=True, null=False,       )
    time_s = models.TimeField(blank=True)
    time_f = models.TimeField(blank=True)
    days = models.CharField(max_length=200, db_index=True, verbose_name='Рабочие дни', blank=True) #не сделан множественный выбор 
    #models.ManyToManyField(Days)
    #models.CharField(max_length=1, choices=DAYS_OF_WEEK) #для выбора из перечисления
    subscription = models.CharField(max_length=500, db_index=True, verbose_name='Абонементы', blank=True)
    # models.ForeignKey(Subscription, on_delete=models.CASCADE, verbose_name='Выберите абонемент')
    schedule = models.CharField(max_length=1000, db_index=True, verbose_name='Расписание', blank=True)
    description = models.CharField(max_length=1000, db_index=True, verbose_name='Описание', blank=True)
    image = models.ImageField(upload_to='sections/%Y/%m/%d', blank=True, verbose_name='Фото')


    class Meta:
        ordering = ('name',)
        verbose_name = 'Секция'
        verbose_name_plural = 'Секции'

    def __str__(self):
        return self.name



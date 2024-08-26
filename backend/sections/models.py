from django.db import models
from django.urls import reverse
from phonenumber_field.modelfields import PhoneNumberField

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
    
#class Type(models.Model):
#    """Виды деятельности, к которым относятся секции"""
#    type_name = models.CharField(max_length=150, db_index=True, verbose_name='Вид деятельности')
    

#    class Meta:
#        ordering = ('type_name',)
#        verbose_name = 'Вид деятельности'
#        verbose_name_plural = 'Виды деятельности'

#    def __str__(self):
#        return self.type_name
    
class Section(models.Model):
    """Модель описания секции"""
    category_name = models.ForeignKey(Category, on_delete=models.CASCADE,
                                 verbose_name='Выберите направление')
    type = models.CharField(max_length=200, db_index=True, verbose_name='Выберите вид деятельности', blank=True, null=True)
    name = models.CharField(max_length=200, db_index=True, verbose_name='Название секции')
    address = models.CharField(max_length=200, db_index=True, verbose_name='Адрес')
    age_s = models.DecimalField(max_digits=2, decimal_places=0, verbose_name='Возраст от')
    age_f = models.DecimalField(max_digits=2, decimal_places=0, verbose_name='Возраст до')
    phone_number = PhoneNumberField('Контакты',unique=True, blank=True, null=True)
    time_s = models.TimeField()
    time_f = models.TimeField()
    # день недели days = models.
    subscription = models.CharField(max_length=500, db_index=True, verbose_name='Абонементы')
    schedule = models.CharField(max_length=1000, db_index=True, verbose_name='Расписание')
    description = models.CharField(max_length=1000, db_index=True, verbose_name='Описание')
    image = models.ImageField(upload_to='sections/%Y/%m/%d', blank=True, verbose_name='Фото')


    class Meta:
        ordering = ('name',)
        verbose_name = 'Секция'
        verbose_name_plural = 'Секции'

    def __str__(self):
        return self.name



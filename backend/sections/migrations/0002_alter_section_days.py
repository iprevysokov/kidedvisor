# Generated by Django 4.2.3 on 2024-09-02 07:46

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('sections', '0001_initial'),
    ]

    operations = [
        migrations.AlterField(
            model_name='section',
            name='days',
            field=models.CharField(db_index=True, max_length=200, verbose_name='Рабочие дни'),
        ),
    ]

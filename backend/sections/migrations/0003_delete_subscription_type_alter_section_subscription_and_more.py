# Generated by Django 4.2.3 on 2024-08-30 06:55

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('sections', '0002_alter_section_type_name_delete_type'),
    ]

    operations = [
        migrations.DeleteModel(
            name='subscription_type',
        ),
        migrations.AlterField(
            model_name='section',
            name='subscription',
            field=models.CharField(db_index=True, max_length=500, verbose_name='Абонементы'),
        ),
        migrations.DeleteModel(
            name='Subscription',
        ),
    ]

# Generated by Django 4.2.3 on 2024-09-09 08:51

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('sections', '0005_alter_sectionimage_section_image'),
    ]

    operations = [
        migrations.AlterField(
            model_name='section',
            name='image',
            field=models.FileField(blank=True, upload_to='', verbose_name='Главное фото'),
        ),
    ]
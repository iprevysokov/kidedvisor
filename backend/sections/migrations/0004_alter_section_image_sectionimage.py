# Generated by Django 4.2.3 on 2024-09-09 07:32

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('sections', '0003_section_image'),
    ]

    operations = [
        migrations.AlterField(
            model_name='section',
            name='image',
            field=models.FileField(blank=True, default=1, upload_to=''),
            preserve_default=False,
        ),
        migrations.CreateModel(
            name='SectionImage',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('images', models.FileField(upload_to='images/')),
                ('section_image', models.ForeignKey(default=None, on_delete=django.db.models.deletion.CASCADE, to='sections.section')),
            ],
        ),
    ]

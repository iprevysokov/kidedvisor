import os

from django.core.management.base import BaseCommand
from django.contrib.auth import get_user_model
from django.db.utils import IntegrityError

from dotenv import load_dotenv

load_dotenv()

User = get_user_model()


class Command(BaseCommand):
    """Команда для создания Суперпользователя."""

    help = 'Создание суперпользователя в базе данных'

    def handle(self, *args, **options):
        email = os.getenv('SUPERUSER_EMAIL')
        password = os.getenv('SUPERUSER_PASSWORD')

        try:
            User.objects.create_superuser(
                email=email,
                password=password,
            )
        except IntegrityError:
            self.stdout.write(
                self.style.SUCCESS('Суперпользователь уже существует')
                )
        else:
            self.stdout.write(self.style.SUCCESS('Суперпользователь создан'))

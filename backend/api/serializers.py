from rest_framework import serializers
from users.models import User, RolesUser


class UserSerializer(serializers.ModelSerializer):
    """
    Сериализатор для работы с пользователями.
    Сохраняет пользователя, если его нет в базе. С заданной ролью.
    """

    class Meta:
        model = User
        fields = (
            'email',
            'phone_number',
            'first_name',
            'middle_name',
            'last_name',
        )

    def validate(self, data):
        """
        Валидация данных. Проверка на наличие номера телефона.
        Проверка на уровне входных данных.
        В моделе User поле phone_number как не обязательное поле.
        """

        if not data['phone_number']:
            raise serializers.ValidationError(
                'Номер телефона обязательное поле.'
            )

        return data

    def create(self, validated_data):
        """
        Сохраняем пользователя, с помощью create_user.
        Данный метод вызывается на уровне модели UserManager(BaseUserManager).
        переопределенного менеджера пользователя.
        """

        user = User.objects.create_user(**validated_data)

        return user

    def save_with_role(self, role):
        """
        Сохраняем пользователя с заданной ролью.
        """
        # создаем или получаем пользователя
        user = self.create(self.validated_data)

        RolesUser.objects.create(user=user, role=role)
        return

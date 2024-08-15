from rest_framework import serializers
from users.models import User, RolesUser


class UserSerializer(serializers.ModelSerializer):
    """Сериализатор регистрации пользователя."""

    class Meta:
        model = User
        fields = (
            'email',
            'phone_number',
            'first_name',
            'last_name',
        )

    def validate(self, data):
        """Валидация данных."""

        if not data['phone_number']:
            raise serializers.ValidationError(
                'Номер телефона обязательное поле.'
            )

        return data

    def create(self, validated_data):
        """
        Создание пользователя.
        """

        user = User(**validated_data)
        user.set_unusable_password()
        user.save()
        return user

    def save_with_role(self, role):
        """
        Сохраняем пользователя с заданной ролью.
        """
        # создаем или получаем пользователя
        user = self.create(self.validated_data)

        RolesUser.objects.create(user=user, role=role)
        return

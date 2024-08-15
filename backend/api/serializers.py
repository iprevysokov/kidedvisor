from rest_framework import serializers
from users.models import User, RolesUser


class UserSerializer(serializers.ModelSerializer):
    """Сериализатор регистрации пользователя."""

    role = serializers.ChoiceField(
        choices=RolesUser.ROLE_CHOICES,
        default=None,
        )

    class Meta:
        model = User
        fields = (
            'email',
            'phone_number',
            'first_name',
            'last_name',
            'role'
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
        Создание пользователя или добавление роли существующему пользователю.
        """

        role = validated_data.pop('role')  # Извлекаем роль из данных
        email = validated_data.get('email')

        # Пытаемся найти пользователя с таким email,
        # если не найден, создаем нового
        user, user_created = User.objects.get_or_create(
            email=email, defaults=validated_data
            )

        if user_created:
            # Если пользователь создан, сохраняем роль
            RolesUser.objects.create(user=user, role=role)

        else:
            # Если пользователь уже существовал, проверяем наличие роли
            RolesUser.objects.get_or_create(user=user, role=role)

        return user

from django.core.validators import validate_email
from django.core.exceptions import ValidationError as DjangoValidationError
from rest_framework import serializers
from rest_framework_simplejwt.serializers import TokenRefreshSerializer
from rest_framework_simplejwt.tokens import RefreshToken
from users.models import User, RolesUser
from phonenumbers import (
    NumberParseException, is_valid_number, parse as parse_phone_number
    )


class UserSerializer(serializers.ModelSerializer):
    """
    Сериализатор для работы с пользователями.
    Поддерживает запись, чтение и обновление данных пользователя.
    """

    class Meta:
        model = User
        fields = (
            'email',
            'phone_number',
            'first_name',
            'middle_name',
            'last_name',
            'image',
        )

    def validate(self, data):
        """
        Выполняет валидацию данных. Проверяет наличие номера телефона.

        Хотя в модели User поле phone_number не является обязательным,
        здесь оно проверяется на наличие.
        """

        request = self.context.get('request')

        if request.method == 'POST':

            if not data['phone_number']:
                raise serializers.ValidationError(
                    'Номер телефона обязательное поле.'
                )

        return data

    def create(self, validated_data):
        """
        Создает пользователя с использованием метода create_user.

        Метод create_user вызывается на уровне модели
          UserManager(BaseUserManager),
        который является переопределенным менеджером пользователя.
        """

        user = User.objects.create_user(**validated_data)

        return user

    def save_with_role(self, role):
        """
        Сохраняем пользователя с заданной ролью.
        """

        user = self.create(self.validated_data)

        RolesUser.objects.create(user=user, role=role)
        return user


class LoginSerializer(serializers.Serializer):
    field = serializers.CharField(
        required=True,
        allow_blank=False,
        error_messages={
            'required': 'Это поле обязательно для заполнения.',
            'blank': 'Поле не может быть пустым.',
        }
        )

    def validate(self, data):
        validate_field = data.get('field')

        if not validate_field or len(validate_field) == 0:
            raise serializers.ValidationError('Поле не может быть пустым.')

        # Проверка на номер телефона
        if validate_field.startswith('+'):
            try:
                # Проверка номера телефона
                phone_number = parse_phone_number(validate_field, 'RU')
                if is_valid_number(phone_number):
                    user = User.objects.get(phone_number=validate_field)
                    role = 'parent'
                else:
                    raise serializers.ValidationError(
                        'Неверный номер телефона.'
                        )
            except (NumberParseException, User.DoesNotExist):
                raise serializers.ValidationError(
                    'Пользователь с таким номером телефона не найден.'
                    )

        # Проверка на email
        elif '@' in validate_field:
            try:
                validate_email(validate_field)
                user = User.objects.get(email=validate_field)
                role = 'owner'
            except (DjangoValidationError, User.DoesNotExist):
                raise serializers.ValidationError(
                    'Пользователь с таким email не найден.'
                    )

        # Проверка на роль модератора
        if RolesUser.objects.filter(user=user, role='moderator').exists():
            if RolesUser.objects.filter(user=user).count() > 1:
                raise serializers.ValidationError(
                    'Модератор не может иметь другие роли.'
                    )
            role = 'moderator'
        check_rolle_user = list(
            RolesUser.objects.filter(user=user).values_list('role', flat=True)
        )

        if role not in check_rolle_user:
            role_description = dict(RolesUser.ROLE_CHOICES).get(role)
            raise serializers.ValidationError(
                f'Пользователь не зарегтстрирован в роли: {role_description}.'
                )

        data.pop('field', None)
        data['user'] = user
        data['role'] = role
        return data


class CustomTokenRefreshSerializer(TokenRefreshSerializer):
    """Сериализатор для обновления токена."""

    def validate(self, attrs):
        # Получаем результат от родительского метода
        data = super().validate(attrs)

        # Получаем refresh_token из запроса
        refresh_token_str = attrs.get('refresh')
        refresh_token = RefreshToken(refresh_token_str)

        # Сохраняем роль в новом access токене
        access_token = refresh_token.access_token
        access_token['role'] = refresh_token.get('role', None)

        # Обновляем данные новым access токеном с ролью
        data['access'] = str(access_token)

        # Возвращаем обновленные данные
        return data

from django.core.validators import validate_email
from django.core.exceptions import ValidationError as DjangoValidationError
from rest_framework import serializers
from users.models import User, RolesUser
from phonenumbers import NumberParseException, is_valid_number, parse as parse_phone_number


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
    field = serializers.CharField()

    def validate(self, data):
        validate_field = data.get('field')

        if not validate_field or len(validate_field) == 0:
            raise serializers.ValidationError('Поле не может быть пустым.')

        # Проверка на номер телефона
        elif validate_field.startswith('+'):
            try:
                # Парсим номер телефона, указываем код страны (по умолчанию Россия: 'RU')
                phone_number = parse_phone_number(validate_field, 'RU')
                if is_valid_number(phone_number):  # Проверка валидности номера
                    data['phone_number'] = validate_field
                else:
                    raise serializers.ValidationError('Неверный номер телефона.')
            except NumberParseException:
                raise serializers.ValidationError('Неверный формат номера телефона.')

        # Проверка на email
        elif '@' in validate_field:
            try:
                validate_email(validate_field)
                data['email'] = validate_field
            except DjangoValidationError:
                raise serializers.ValidationError('Неверный формат email.')

        # Если данные не подходят ни под email, ни под телефон
        else:
            raise serializers.ValidationError('Введите корректные данные.')

        # Удаляем поле 'field' из data
        data.pop('field', None)

        return data
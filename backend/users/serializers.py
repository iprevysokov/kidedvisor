from rest_framework import serializers
from users.models import User, RolesUser
from rest_framework_simplejwt.serializers import TokenObtainPairSerializer


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
        return


class UserTokenObtainPairSerializer(TokenObtainPairSerializer):
    @classmethod
    def get_token(cls, user):
        token = super().get_token(user)

        token['first_name'] = user.first_name
        token['middle_name'] = user.middle_name
        token['last_name'] = user.last_name
        token['username'] = user.username

        return token

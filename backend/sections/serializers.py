from rest_framework import serializers
from sections.models import Category, Section


class SectionSerializer(serializers.ModelSerializer):
    """
    Сериализатор для работы с секциями.
    Поддерживает запись, чтение и обновление данных о секциях.
    """

    class Meta:
        model = Section
        fields = ( 
            'category_name',
            'type_name',
            'name',
            'address',
            'age_s',
            'age_f',
           # 'phone_number',
           # 'time_s',
           # 'time_f',
           # 'subscription',
           # 'schedule',
           # 'description',
           # 'image'
        )

    def validate(self, data):
        """
        Выполняет валидацию данных. Проверяет заполнение полей.
        """

        if not data['name']:
            raise serializers.ValidationError(
                'Наименование секции обязательное поле.'
            )

        return data

    def create(self, validated_data):
        """
        Создает секцию с использованием метода create_section.

        """
        Section = Section.objects.register_section(**validated_data)

        return Section
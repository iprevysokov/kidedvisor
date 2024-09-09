from rest_framework import serializers
from sections.models import Section


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
            'work_day_mon',
            'work_day_tue',
            'work_day_wed',
            'work_day_thu',
            'work_day_fri',
            'work_day_sat',
            'work_day_sun',
            'email',
            'phone_number',
            'subscription',
            'schedule',
            'description',
            'image',
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
        section = Section.objects.create(**validated_data)

        return section

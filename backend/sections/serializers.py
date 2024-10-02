from rest_framework import serializers
from sections.models import Section, SectionImage


class SectionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Section
        fields = (
            'category_name',
            'type_name',
            'name',
            'city',
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
            'tg_contact',
            'whatsapp',
            'phone_number',
            'subscription',
            'schedule',
            'description',
        )

        # model = SectionImage
        # fields = (
        #     "section_image",
        #     "images",
        #     "order",
        # )

    def validate(self, data):
        """
        Выполняет валидацию данных. Проверяет заполнение полей.
        """

        if not data["name"]:
            raise serializers.ValidationError(
                "Наименование секции обязательное поле."
        )

        return data

    def create(self, validated_data):
        """
        Создает секцию с использованием метода create_section.

        """
        section = Section.objects.create(**validated_data)

        return section


class SectionImageSerializer(serializers.ModelSerializer):
    images = serializers.ImageField()

    class Meta:
        model = SectionImage
        fields = ('id', 'section_image', 'images', 'order')
        read_only_fields = ['section_image']  # 'section_image' устанавливается программно

    def create(self, validated_data):
        section_image = self.context['section']  # Получаем секцию из контекста
        image_data = validated_data.pop('images')
        order = validated_data.pop('order')
        return SectionImage.objects.create(section_image=section_image, images=image_data, order=order)

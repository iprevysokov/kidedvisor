from rest_framework import serializers
from .models import ModerationRequest

class ModerationRequestSerializer(serializers.ModelSerializer):
    class Meta:
        model = ModerationRequest
        fields = ['id', 'text', 'response', 'status', 'user', 'created_at', 'updated_at']
        read_only_fields = ['status', 'created_at', 'updated_at']

    def update(self, instance, validated_data):
        # При обновлении ответа статус меняется на "Завершено"
        if 'response' in validated_data and validated_data['response']:
            instance.status = 'completed'
        return super().update(instance, validated_data)
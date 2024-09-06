from rest_framework import serializers
from .models import HelpRequest


class HelpRequestSerializer(serializers.ModelSerializer):
    class Meta:
        model = HelpRequest
        fields = [
            "id",
            "text",
            "response",
            "status",
            "user",
            "created_at",
            "updated_at",
            "response_sent_at"
        ]
        read_only_fields = ["status", "created_at", "updated_at", "response_sent_at"]

    def update(self, instance, validated_data):
        # При обновлении ответа статус меняется на "Рассмотрение заявки"
        if "response" in validated_data and validated_data["response"]:
            instance.status = "in_moderation"
        return super().update(instance, validated_data)

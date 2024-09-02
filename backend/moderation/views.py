from rest_framework import viewsets, status
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from .models import ModerationRequest
from .serializers import ModerationRequestSerializer
from django.core.mail import send_mail

class ModerationRequestViewSet(viewsets.ModelViewSet):
    queryset = ModerationRequest.objects.all()
    serializer_class = ModerationRequestSerializer
    permission_classes = [IsAuthenticated]

    def perform_create(self, serializer):
        # Установим пользователя и создадим запрос
        serializer.save(user=self.request.user)

    def update(self, request, *args, **kwargs):
        instance = self.get_object()
        response = request.data.get('response', None)

        # Смена статуса на "На модерации" при открытии обращения
        if instance.status == 'queued':
            instance.status = 'in_moderation'
            instance.save()

        # Смена статуса на "Завершено" и отправка ответа по электронной почте
        if response:
            instance.status = 'completed'
            instance.save()
            send_mail(
                subject='Ответ на ваше обращение',
                message=response,
                from_email='noreply@example.com',
                recipient_list=[instance.user.email],
            )
        return super().update(request, *args, **kwargs)
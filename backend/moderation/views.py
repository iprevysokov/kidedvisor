from django.utils import timezone
from smtplib import SMTPException
from django.http import BadHeaderError
from rest_framework import viewsets, status
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from .models import HelpRequest
from .serializers import HelpRequestSerializer
from django.core.mail import send_mail


class HelpRequestViewSet(viewsets.ModelViewSet):
    queryset = HelpRequest.objects.all()
    serializer_class = HelpRequestSerializer
    permission_classes = [IsAuthenticated]

    def perform_create(self, serializer):
        # Установим пользователя и создадим запрос
        serializer.save(user=self.request.user)

    def update(self, request, *args, **kwargs):
        instance = self.get_object()
        response = request.data.get("response", None)

        # Смена статуса на "На модерации" при открытии обращения
        if instance.status == "queued":
            instance.status = "in_moderation"
            instance.save()

        # Попытка отправки письма и смена статуса на "Завершено" только после успешной отправки
        if response:
            try:
                # Формируем текст письма, включая текст обращения и ответ
                email_message = (
                    f"Ваше обращение:\n\n{instance.text}\n\nОтвет на ваше обращение:\n\n{response}"
                )

                send_mail(
                    subject="Ответ на ваше обращение",
                    message=email_message,
                    from_email="noreply@example.com",
                    recipient_list=[instance.user.email],
                )
                # Смена статуса на "Завершено" только если отправка прошла успешно
                instance.status = "completed"
                instance.response_sent_at = timezone.now()  # Set the response sent date
                instance.save()
            except (BadHeaderError, SMTPException) as e:
                # Обработка ошибок отправки письма
                return Response(
                    {"detail": f"Ошибка при отправке письма: {str(e)}"},
                    status=status.HTTP_500_INTERNAL_SERVER_ERROR,
                )

        return super().update(request, *args, **kwargs)

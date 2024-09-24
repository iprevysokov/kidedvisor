from django.utils import timezone
from smtplib import SMTPException
from django.http import BadHeaderError
from rest_framework import viewsets, status, filters
from rest_framework.response import Response
from rest_framework.permissions import IsAuthenticated
from .models import HelpRequest
from .serializers import HelpRequestSerializer
from django.core.mail import send_mail
from django_filters.rest_framework import DjangoFilterBackend
from rest_framework.decorators import action
from users.models import User
from users.serializers import UserSerializer
from rest_framework.permissions import AllowAny

class HelpRequestViewSet(viewsets.ModelViewSet):
    queryset = HelpRequest.objects.all()
    serializer_class = HelpRequestSerializer
    permission_classes = [AllowAny]
    # permission_classes = [IsAuthenticated]

    filter_backends = [
        DjangoFilterBackend,
        filters.OrderingFilter,
        filters.SearchFilter,
    ]
    filterset_fields = ["status"]  # Добавьте статус в фильтры
    ordering_fields = ["created_at", "updated_at"]  # Поля, по которым можно сортировать
    search_fields = ["text", "response"]  # Поля, по которым можно выполнять поиск

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
                email_message = f"Ваше обращение:\n\n{instance.text}\n\nОтвет на ваше обращение:\n\n{response}"

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

    def send_response_email(self, instance, response):
        """
        Вспомогательный метод для отправки письма с ответом на обращение.
        """
        email_message = f"Ваше обращение:\n\n{instance.text}\n\nОтвет на ваше обращение:\n\n{response}"
        send_mail(
            subject="Ответ на ваше обращение",
            message=email_message,
            from_email="noreply@example.com",
            recipient_list=[instance.user.email],
        )

    @action(detail=True, methods=["post"], url_path="retry-send")
    def retry_send_response(self, request, pk=None):
        """
        Метод для повторной отправки ответа по электронной почте.
        """
        instance = self.get_object()

        # Проверяем, есть ли ответ и статус не "completed"
        if not instance.response:
            return Response(
                {"detail": "Ответ не найден, повторная отправка невозможна."},
                status=status.HTTP_400_BAD_REQUEST,
            )

        if instance.status == "completed":
            return Response(
                {"detail": "Ответ уже был успешно отправлен."},
                status=status.HTTP_400_BAD_REQUEST,
            )

        # Пытаемся повторно отправить письмо
        try:
            self.send_response_email(instance, instance.response)
            instance.status = "completed"
            instance.response_sent_at = timezone.now()
            instance.save()
            return Response(
                {"detail": "Ответ успешно отправлен повторно."},
                status=status.HTTP_200_OK,
            )
        except (BadHeaderError, SMTPException) as e:
            return Response(
                {"detail": f"Ошибка при повторной отправке письма: {str(e)}"},
                status=status.HTTP_500_INTERNAL_SERVER_ERROR,
            )


class UserModerationViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer
    permission_classes = [AllowAny]
    # permission_classes = [IsAuthenticated]

    filter_backends = [
        DjangoFilterBackend,
        filters.OrderingFilter,
        filters.SearchFilter,
    ]
    filterset_fields = ["status"]  # Фильтр по статусу
    search_fields = ["email", "first_name", "last_name"]  # Поиск по email и имени

    @action(detail=True, methods=["post"])
    def block_user(self, request, pk=None):
        user = self.get_object()
        user.status = "blocked"
        user.save()
        return Response(
            {"detail": "Пользователь заблокирован."}, status=status.HTTP_200_OK
        )

    @action(detail=True, methods=["post"])
    def unblock_user(self, request, pk=None):
        user = self.get_object()
        if user.status == "blocked":
            user.status = "active"
            user.save()
            return Response(
                {"detail": "Пользователь разблокирован."}, status=status.HTTP_200_OK
            )
        return Response(
            {"detail": "Пользователь не заблокирован."},
            status=status.HTTP_400_BAD_REQUEST,
        )

    @action(detail=True, methods=["delete"])
    def delete_user(self, request, pk=None):
        user = self.get_object()
        user.delete()
        return Response(
            {"detail": "Спасибо! Пользователь был удален по правам администратора."},
            status=status.HTTP_200_OK,
        )

    @action(detail=True, methods=["post"])
    def send_remark(self, request, pk=None):
        user = self.get_object()
        remark = request.data.get("remark")

        # Проверка наличия текста замечания
        if not remark:
            return Response(
                {"detail": "Замечание не может быть пустым."},
                status=status.HTTP_400_BAD_REQUEST,
            )

        # Формирование текста письма
        email_message = f"""
        Уважаемый(ая) {user.first_name},

        Мы хотели бы сообщить вам следующее замечание:

        "{remark}"

        Если у вас есть вопросы или вы хотите обсудить это замечание, пожалуйста, свяжитесь с нами.

        С уважением,
        Ваша команда поддержки
        """

        # Попытка отправить письмо
        try:
            send_mail(
                subject="Замечание от администрации",
                message=email_message,
                from_email="noreply@example.com",  # Укажите реальный адрес отправителя
                recipient_list=[user.email],
            )
            return Response(
                {"detail": "Замечание успешно отправлено."}, status=status.HTTP_200_OK
            )
        except (BadHeaderError, SMTPException) as e:
            return Response(
                {"detail": f"Ошибка при отправке письма: {str(e)}"},
                status=status.HTTP_500_INTERNAL_SERVER_ERROR,
            )

from django.utils import timezone
from django.db import models
from users.models import User


class HelpRequest(models.Model):
    STATUS_CHOICES = [
        ("queued", "На очереди"),
        ("in_moderation", "Рассмотрение заявки"),
        ("completed", "Завершено"),
    ]

    text = models.TextField(verbose_name="Текст обращения")
    response = models.TextField(verbose_name="Ответ", blank=True, null=True)
    status = models.CharField(
        max_length=20, choices=STATUS_CHOICES, default="queued", verbose_name="Статус"
    )
    user = models.ForeignKey(
        User,
        on_delete=models.CASCADE,
        related_name="help_requests",
        verbose_name="Пользователь",
    )
    created_at = models.DateTimeField(auto_now_add=True, verbose_name="Дата создания")
    updated_at = models.DateTimeField(auto_now=True, verbose_name="Дата обновления")
    response_sent_at = models.DateTimeField(verbose_name="Дата отправки ответа", blank=True, null=True)

    def save(self, *args, **kwargs):
        # При сохранении статуса "На очереди", изменяем статус на "На модерации"
        if self.pk and self.status == "queued":
            self.status = "in_moderation"
        super().save(*args, **kwargs)

    class Meta:
        verbose_name = "Запрос на модерацию"
        verbose_name_plural = "Запросы на модерацию"
        ordering = ("id",)

    def __str__(self):
        return f"{self.text[:30]} - {self.get_status_display()}"

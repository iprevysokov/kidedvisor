# moderation/middleware.py

from django.http import JsonResponse
from django.contrib.auth.middleware import get_user
from django.utils.deprecation import MiddlewareMixin

class BlockedUserMiddleware(MiddlewareMixin):
    """
    Middleware для блокировки запросов от пользователей со статусом 'blocked'.
    """

    def process_request(self, request):
        # Получаем пользователя из запроса
        user = get_user(request)

        # Проверяем статус пользователя, если он аутентифицирован
        if user.is_authenticated:
            print(f"Пользователь: {user.email}, Статус: {user.status}")  # Лог для отладки
            if user.status == 'blocked':
                # Если пользователь заблокирован, возвращаем сообщение об ошибке
                return JsonResponse(
                    {"detail": "Ваш аккаунт заблокирован. Обратитесь в поддержку."},
                    status=403
                )
        # Если пользователь не заблокирован, продолжаем выполнение запроса
        return None

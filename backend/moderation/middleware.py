from django.http import JsonResponse

class BlockedUserMiddleware:
    """
    Middleware для блокировки запросов от пользователей со статусом 'blocked'.
    """

    def __init__(self, get_response):
        self.get_response = get_response

    def __call__(self, request):
        user = getattr(request, 'user', None)
        if user and user.is_authenticated and getattr(user, 'status', '') == 'blocked':
            # Если пользователь заблокирован, возвращаем сообщение об ошибке
            return JsonResponse(
                {"detail": "Ваш аккаунт заблокирован. Обратитесь в поддержку."},
                status=403
            )
        response = self.get_response(request)
        return response

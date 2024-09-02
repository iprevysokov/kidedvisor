from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import ModerationRequestViewSet

app_name = 'moderation'

router = DefaultRouter()
router.register(r'moderation-requests', ModerationRequestViewSet)

urlpatterns = [
    path('', include(router.urls)),
]

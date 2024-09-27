from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import HelpRequestViewSet, UserModerationViewSet

app_name = 'moderation'

router = DefaultRouter()
router.register(r'help-requests', HelpRequestViewSet)
router.register(r'user-moderation', UserModerationViewSet, basename='user-moderation')

urlpatterns = [
    path('', include(router.urls)),
]

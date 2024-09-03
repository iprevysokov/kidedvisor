from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import HelpRequestViewSet

app_name = 'moderation'

router = DefaultRouter()
router.register(r'help-requests', HelpRequestViewSet)

urlpatterns = [
    path('', include(router.urls)),
]

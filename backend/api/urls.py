from rest_framework.routers import DefaultRouter
from django.urls import include, path

from .views import UserViewSet


app_name = 'api'

router_v1 = DefaultRouter()

router_v1.register('users', UserViewSet, basename='users')


urlpatterns = [
    path('', include(router_v1.urls)),
]

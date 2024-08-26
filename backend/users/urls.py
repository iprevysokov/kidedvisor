from rest_framework.routers import DefaultRouter
from django.urls import include, path

from .views import UserViewSet


app_name = 'users'

router_v1 = DefaultRouter()

router_v1.register('', UserViewSet, basename='users')


urlpatterns = [
    path('', include(router_v1.urls)),
]

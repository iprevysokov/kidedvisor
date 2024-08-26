from rest_framework.routers import DefaultRouter
from django.urls import include, path

from .views import SectionViewSet


app_name = 'sections'

router_v1 = DefaultRouter()

router_v1.register('', SectionViewSet, basename='sections')


urlpatterns = [
    path('', include(router_v1.urls)),
]
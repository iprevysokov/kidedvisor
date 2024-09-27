from rest_framework.routers import DefaultRouter
from django.urls import include, path

from .views import (UserViewSet, ExchangeTokenView, RefreshAccessTokenView,
                    CustomLoginView, ChangeUserRoleView)

app_name = 'users'

router_v1 = DefaultRouter()

router_v1.register('', UserViewSet, basename='users')


urlpatterns = [
    path('auth/login/', CustomLoginView.as_view(), name='login'),
    path(
        'auth/token/refresh/',
        RefreshAccessTokenView.as_view(),
        name='token_refresh'
        ),
    path(
        'auth/change-role/', ChangeUserRoleView.as_view(), name='change_role'
        ),
    path(
        'auth/exchange-token/',
        ExchangeTokenView.as_view(),
        name='exchange_token'
        ),
    path('', include(router_v1.urls)),
]

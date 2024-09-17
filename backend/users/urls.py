from rest_framework.routers import DefaultRouter
from django.urls import include, path

from .views import UserViewSet,ExchangeTokenView, RefreshAccessTokenView, CustomLoginView, ChangeUserRoleView

app_name = 'users'

router_v1 = DefaultRouter()

router_v1.register('', UserViewSet, basename='users')


urlpatterns = [
    path('login/', CustomLoginView.as_view() , name='login'),
    path('token/exchange/', ExchangeTokenView.as_view(), name='token_exchange'),
    path('token/refresh/', RefreshAccessTokenView.as_view(), name='token_refresh'),
    path('token/change_role/', ChangeUserRoleView.as_view(), name='token_change_role'),
    path('', include(router_v1.urls)),
]

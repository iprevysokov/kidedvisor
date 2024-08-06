
from django.contrib import admin
from django.conf import settings
from django.urls import path, re_path

from rest_framework import permissions
from drf_yasg.views import get_schema_view
from drf_yasg import openapi


urlpatterns = [
    path('admin/', admin.site.urls),
]

if settings.DEBUG:
    schema_view = get_schema_view(
        openapi.Info(
            title="Kidedvisor API",
            default_version='v1',
            description="API для работы с приложением Kidedvisor",
        ),
        public=True,
        permission_classes=(permissions.AllowAny,),
    )

    urlpatterns += [
        re_path(r'^swagger(?P<format>\.json|\.yaml)$', schema_view.without_ui(
            cache_timeout=0), name='schema-json'),
        re_path(r'^swagger/$', schema_view.with_ui('swagger', cache_timeout=0),
                name='schema-swagger-ui'),
        re_path(r'^redoc/$', schema_view.with_ui('redoc', cache_timeout=0),
                name='schema-redoc'),
    ]

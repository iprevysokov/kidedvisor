from typing import Any
from django.contrib import admin
from django.contrib.auth.admin import UserAdmin

from .models import User, RolesUser


admin.site.empty_value_display = '-Не задано-'


@admin.register(User)
class CustomUserAdmin(UserAdmin):
    """Административная модель пользователя."""

    list_display = (
        'email', 'phone_number', 'first_name',
        'last_name', 'date_joined', 'is_active',
        )

    search_fields = ('email', 'phone_number', 'first_name', 'last_name')

    list_filter = ('date_joined',)

    readonly_fields = ('last_login', 'date_joined',)

    fieldsets = (
        ('Личные данные', {
            'fields': ('first_name', 'last_name')
            }),
        ('Доcтуп', {
            'fields': ('is_staff', 'is_superuser', 'is_active')
            }),
    )

    add_fieldsets = (
        ('Основные данные', {
            'fields': ('email', 'phone_number')
            }),
        ('Личные данные', {
            'fields': ('first_name', 'last_name')
            }),
        ('Доступ', {
            'fields': ('is_staff', 'is_superuser', 'is_active')
            }),
    )

    ordering = ('date_joined',)

    def save_model(self, request: Any, obj: Any, form: Any, change: Any) -> None:
        if not change:
            obj.set_unusable_password()
        return super().save_model(request, obj, form, change)


@admin.register(RolesUser)
class RolesUserAdmin(admin.ModelAdmin):
    pass

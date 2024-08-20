
from django.contrib import admin
from django.contrib.auth.admin import UserAdmin
from django import forms
from django.utils.safestring import mark_safe

from .models import User, RolesUser

from kidedvisor.constant import NO_VALUE

admin.site.empty_value_display = NO_VALUE


class CustomUserForm(forms.ModelForm):
    """Форма для создания нового пользователя в админке без пароля."""

    role = forms.ChoiceField(choices=RolesUser.ROLE_CHOICES, required=True)

    class Meta:
        model = User
        fields = (
            'email', 'phone_number', 'first_name', 'last_name',
            'is_staff', 'is_superuser', 'is_active'
        )


@admin.register(User)
class CustomUserAdmin(UserAdmin):
    """Административная модель пользователя."""

    add_form = CustomUserForm

    list_display = (
        'get_image', 'email', 'phone_number', 'first_name',
        'last_name', 'date_joined', 'is_active',
        )

    search_fields = ('email', 'phone_number', 'first_name', 'last_name')

    list_filter = ('date_joined',)

    readonly_fields = ('last_login', 'date_joined',)

    fieldsets = (
        ('Личные данные', {
            'fields': ('first_name', 'last_name',)
            }),
        ('Доcтуп', {
            'fields': ('is_staff', 'is_superuser', 'is_active')
            }),
    )

    add_fieldsets = (
        ('Основные данные', {
            'fields': ('email', 'phone_number', 'role')
            }),
        ('Личные данные', {
            'fields': ('first_name', 'last_name', 'image')
            }),
        ('Доступ', {
            'fields': ('is_staff', 'is_superuser', 'is_active')
            }),
    )

    ordering = ('date_joined',)

    def save_model(self, request, obj, form, change):
        """
        Универсальный метод сохранения в админке.
        Устанавливаем 'неиспользуемый' пароль
        только при создании нового пользователя.
        """

        if not change:
            obj.set_unusable_password()
        super().save_model(request, obj, form, change)

        role = form.cleaned_data.get('role')
        if role and not change:
            RolesUser.objects.create(user=obj, role=role)

    @admin.display(description='Аватар')
    def get_image(self, obj):
        if obj.image:
            return mark_safe(
                f'<img src="{obj.image.url}" width="80" height="60" />'
            )

        return f'{NO_VALUE}'

@admin.register(RolesUser)
class RolesUserAdmin(admin.ModelAdmin):
    """Административная модель ролей пользователя."""

    list_display = ('user', 'role', 'date_joined')
    list_filter = ('user', 'role')
    search_fields = ('user', 'role')
    ordering = ('user', 'role')
    readonly_fields = ('date_joined',)

    fieldsets = (
        ('Присвоение роли', {
            'fields': ('user', 'role')
            }),
    )
    add_fieldsets = (
        ('Присвоение роли', {
            'fields': ('user', 'role')
            }),
    )

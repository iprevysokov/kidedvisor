from django.contrib import admin
from .models import Category, Section


class CategoryAdmin(admin.ModelAdmin):
    list_display = ['category_name', 'id']


admin.site.register(Category, CategoryAdmin)

# class SubscriptionAdmin(admin.ModelAdmin):
#    list_display = ['subscription_name']

# admin.site.register(Subscription, SubscriptionAdmin)


class SectionAdmin(admin.ModelAdmin):
    list_display = ['name', 'address', 'age_s', 'age_f', 'id']
    list_filter = ['name', 'address', 'age_s', 'age_f']


admin.site.register(Section, SectionAdmin)

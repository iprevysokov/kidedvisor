from django.contrib import admin
from .models import Category, Section

class CategoryAdmin(admin.ModelAdmin):
    list_display = ['category_name']

admin.site.register(Category, CategoryAdmin)

#class TypeAdmin(admin.ModelAdmin):
#    list_display = ['type_name']

#admin.site.register(Type, TypeAdmin)

class SectionAdmin(admin.ModelAdmin):
    list_display = ['name', 'address', 'age_s', 'age_f']
    list_filter = ['name', 'address', 'age_s', 'age_f']

admin.site.register(Section, SectionAdmin)
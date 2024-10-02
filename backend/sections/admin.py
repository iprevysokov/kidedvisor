from django.contrib import admin
from .models import Category, Section, SectionImage, Cities


class CategoryAdmin(admin.ModelAdmin):
    list_display = ['category_name', 'id']


admin.site.register(Category, CategoryAdmin)


class CityAdmin(admin.ModelAdmin):
    list_display = ['city', 'id']


admin.site.register(Cities, CityAdmin)

# class SubscriptionAdmin(admin.ModelAdmin):
#    list_display = ['subscription_name']

# admin.site.register(Subscription, SubscriptionAdmin)


class SectionImagesAdmin(admin.StackedInline):
    model = SectionImage
    list_display = ['section_image', 'images']


@admin.register(Section)
class SectionAdmin(admin.ModelAdmin):
    inlines = [SectionImagesAdmin]
    list_display = ['name', 'address', 'age_s', 'age_f', 'id']

    class Meta:
        model = Section


@admin.register(SectionImage)
class SectionImagesAdmin(admin.ModelAdmin):
    pass

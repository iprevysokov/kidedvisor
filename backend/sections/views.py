from django.shortcuts import render
from .models import Category, Section
from rest_framework import viewsets, permissions, status, mixins
from rest_framework.response import Response
from rest_framework.decorators import action

class SectionViewSet(mixins.UpdateModelMixin,
                  mixins.DestroyModelMixin,
                  viewsets.GenericViewSet):

    queryset = Section.objects.all()
#    serializer_class = SectionSerializer

    http_method_names = ['get', 'patch', 'delete', 'post']

def section_list(request):
    """" Страница списка секций"""
    category = Category.objects.all()
#    type = Type.objects.all()
    sections = Section.objects.filter(available=True)
    return render(request)

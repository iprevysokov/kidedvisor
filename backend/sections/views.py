from .models import Section, SectionImage
from rest_framework import viewsets, status, mixins
from rest_framework.response import Response
from rest_framework.decorators import action
from .serializers import SectionImageSerializer, SectionSerializer
from kidedvisor.constant import SUCCESSFUL_REGISTRATION_MESSAGE_SECTION
from rest_framework.parsers import MultiPartParser, FormParser


class SectionViewSet(
    mixins.UpdateModelMixin, mixins.DestroyModelMixin, viewsets.GenericViewSet
):

    queryset = Section.objects.all()
    serializer_class = SectionSerializer
    parser_classes = (MultiPartParser, FormParser)  # Для загрузки файлов

    http_method_names = ["get", "patch", "delete", "post"]

    @action(detail=False, methods=["post"])
    def register_section(self, request):
        """Регистрация секции в системе"""

        return self._register_section(self, request=request)

    @action(detail=True, methods=["post"])
    def upload_image(self, request, pk=None):
        """Загрузка изображения"""
        try:
            section = self.get_object()  # Получаем объект секции
        except Section.DoesNotExist:
            return Response(
                {"error": "Секция не найдена."}, status=status.HTTP_404_NOT_FOUND
            )

        # Передаем section в контекст сериализатора как section_image
        serializer = SectionImageSerializer(
            data=request.data, context={"section": section}
        )

        if serializer.is_valid():
            serializer.save()
            return Response(serializer.data, status=status.HTTP_201_CREATED)

        return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    @action(
        detail=True, methods=["delete"], url_path="delete_image/(?P<image_id>[^/.]+)"
    )
    def delete_image(self, request, pk=None, image_id=None):
        """Удаление изображения по id"""
        try:
            image = SectionImage.objects.get(id=image_id, section_image_id=pk)
            image.delete()
            return Response(
                {"detail": "Изображение удалено"}, status=status.HTTP_204_NO_CONTENT
            )
        except SectionImage.DoesNotExist:
            return Response(
                {"detail": "Изображение не найдено"}, status=status.HTTP_404_NOT_FOUND
            )

    @action(detail=True, methods=["post"], url_path="update_image/(?P<image_id>[^/.]+)")
    def update_image(self, request, pk=None, image_id=None):
        """Замена изображения по его id"""
        try:
            image = SectionImage.objects.get(id=image_id, section_image_id=pk)
            image.images.delete()  # Удаление старого изображения
            serializer = SectionImageSerializer(
                image, data=request.data, partial=True
            )  # Используем SectionImageSerializer
            serializer.is_valid(raise_exception=True)
            serializer.save()
            return Response(serializer.data)
        except SectionImage.DoesNotExist:
            return Response(
                {"detail": "Изображение не найдено"}, status=status.HTTP_404_NOT_FOUND
            )

    @staticmethod
    def _register_section(self, request):
        """
        Общий статический метод для регистрации секции.

        Если секция с данным именем уже существует,
        возвращается сообщение об ошибке.

        Если секция не существует, создается новая секция.
        Возвращает Response с сообщением об успешной регистрации или ошибке.
        """

        section = Section.objects.filter(name=request.data["name"]).first()

        if section:
            if Section.objects.filter(section=section).exists():
                return Response(
                    {"message": "Секция уже зарегистрирована в системе"},
                    status=status.HTTP_400_BAD_REQUEST,
                )

            Section.objects.create(section=section)
            return Response(
                {"message": SUCCESSFUL_REGISTRATION_MESSAGE_SECTION},
                status=status.HTTP_200_OK,
            )

        serializer = SectionSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(
            {"message": SUCCESSFUL_REGISTRATION_MESSAGE_SECTION},
            status=status.HTTP_201_CREATED,
        )

    @action(detail=False, methods=["get"])
    def get_section_info(self, request):
        """Получение информации о секции."""

        section = Section.objects.get(id=request.section.id)
        serializer = self.get_serializer(section)
        return Response(serializer.data)

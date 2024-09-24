from .models import Section, SectionImage
from rest_framework import viewsets, status, mixins
from rest_framework.response import Response
from rest_framework.decorators import action
from .serializers import SectionSerializer
from kidedvisor.constant import SUCCESSFUL_REGISTRATION_MESSAGE_SECTION


class SectionViewSet(mixins.UpdateModelMixin, 
                     mixins.DestroyModelMixin,
                     viewsets.GenericViewSet):

    queryset = Section.objects.all()
    serializer_class = SectionSerializer

    http_method_names = ['get', 'patch', 'delete', 'post']

    @action(detail=False, methods=['post'])
    def register_section(self, request):
        """Регистрация секции в системе."""

        return self._register_section(self, request=request)

    @staticmethod
    def _register_section(self, request):
        """
        Общий статический метод для регистрации секции.

        Если секция с данным именем уже существует,
        возвращается сообщение об ошибке.

        Если секция не существует, создается новая секция.
        Возвращает Response с сообщением об успешной регистрации или ошибке.
        """

        section = Section.objects.filter(name=request.data['name']).first()

        if section:
            if Section.objects.filter(section=section).exists():
                return Response(
                    {'message': 'Секция уже зарегистрирована в системе'},
                    status=status.HTTP_400_BAD_REQUEST,
                )

            Section.objects.create(section=section)
            return Response(
                {'message': SUCCESSFUL_REGISTRATION_MESSAGE_SECTION},
                status=status.HTTP_200_OK
                )

        serializer = SectionSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        serializer.save()
        return Response(
            {'message': SUCCESSFUL_REGISTRATION_MESSAGE_SECTION},
            status=status.HTTP_201_CREATED
            )
    
    @action(detail=False, methods=['post'])
    def upload_image(request, section):
        "Загрузка фото к секции"
        section = Section.objects.all()
        images = request.FILES.getlist('images')
        # order = request.
        # указываю порядок, далее какая-то проверка с порядком
        if section:
            if Section.objects.filter(section=section).exists():
                return section.id
            SectionImage.objects.create(images=images)
            return Response('uspeh')
        serializer = SectionSerializer(images=request.data)
        serializer.save()

    @action(detail=False, methods=['get'])
    def get_section_info(self, request):
        """Получение информации о секции."""

        section = Section.objects.get(id=request.section.id)
        serializer = self.get_serializer(section)
        return Response(serializer.data)

    # @action(detail=False, methods=['get'])
    # def get_section_info(self):
    #     """Получение информации о секции."""
    #     if self.request.method == 'GET':
    #         queryset = Section.objects.all()
    #         id = self.request.GET.get(id, None)
    #         if id is not None:
    #             queryset = queryset.filter(id=id)
    #         return queryset


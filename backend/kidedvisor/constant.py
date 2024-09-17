# users models

MAX_LENGTH_EMAIL_FIELD = 254
MAX_LENGTH_CHAR_FIELD = 150

# admin models users

# Значение для пустого поля по умолчанию
DEFAULT_EMPTY_VALUE = '-Не задано-'

# users views

# def _register_user Сообщение об успешной регистрации
SUCCESSFUL_REGISTRATION_MESSAGE = (
    'Вы успешно зарегистрировались. Вам направлено письмо на email'
    )
INVALID_TOKEN_MESSAGE = ('Не валидный токен вам направленна ссылка на почту для входа в систему')
# users utils

# def send_email_for_user_login Отправка письма пользователю
TEXT_ENTER_APP = 'Добро пожаловать в Kidedvisor'

# ссылка для авторизации, при первичной регистрации пользователя в приложении по access_token
FRONTEND_ACCESS_URL = 'http://localhost:3000'

FRONTEND_LOGIN_URL = 'frontend/users/login/'

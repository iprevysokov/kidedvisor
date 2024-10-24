export const defaultRequiredMessage = 'Данное поле обязательно для заполнения'

export const apiUrl =
    process.env.NODE_ENV == "development"
        ? `http://${process.env.NEXT_PUBLIC_DOMAIN}/api` //todo to testServer
        : `http://${process.env.NEXT_PUBLIC_DOMAIN}/api`;
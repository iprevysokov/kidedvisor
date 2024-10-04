import { ChangeEvent } from "react";
import { apiUrl } from "./constants";
import { IFetch } from "./types";

function checkResponse<T>(res: Response): Promise<T> {
  return res.ok ? res.json() : res.json().then((data) => Promise.reject(data));
}

function _fetch<T>({ url, method = "GET", headers, body }: IFetch): Promise<T> {
  let contentTypeHeader: string | undefined = undefined;
  let authorization = "";
  // Получаем токен авторизации перед каждым запросом
  if (typeof window !== "undefined") {
    authorization = localStorage.getItem("jwt") || "";
  }

  // Устанавливаем заголовок Content-Type в зависимости от типа тела запроса
  if (body instanceof FormData) {
    // Если тело запроса - FormData, не устанавливаем Content-Type (будет установлен автоматически)
  } else if (body) {
    // Если тело запроса - не пустое и не FormData, устанавливаем Content-Type: application/json
    contentTypeHeader = "application/json";
  }

  // Объединяем заголовки с дополнительными заголовками и устанавливаем Content-Type при необходимости
  const mergedHeaders = {
    authorization,
    ...(contentTypeHeader ? { "Content-Type": contentTypeHeader } : {}),
    ...headers,
  };

  const requestBody: BodyInit =
    body instanceof FormData ? body : JSON.stringify(body);

  return fetch(`${apiUrl}/${url}`, {
    method,
    headers: mergedHeaders,
    body: requestBody,
  }).then(checkResponse<T>);
}

// Функция для форматирования номера телефона
export function formatPhoneNumber(input: string): string {
  // Убираем все нецифровые символы для обработки
  const cleanedInput = input.replace(/\D/g, '');

  // Если номер начинается с 8, заменяем её на 7
  let formatted = cleanedInput;
  if (cleanedInput.startsWith('8')) {
    formatted = '7' + cleanedInput.slice(1);
  }

  // Ограничиваем количество цифр до 11 (например, +7 (999) 999-99-99)
  // Но позволяем обрабатывать до 12 цифр для корректного ввода всех символов
  const maxLength = 11;
  if (formatted.length > maxLength) {
    formatted = formatted.slice(0, maxLength);
  }

  // Применяем форматирование только если есть что форматировать
  if (formatted.length === 0) return '';

  // Применяем форматирование
  let result = '+' + formatted; // Добавляем "+7"
  if (formatted.length > 1) {
    result = '+' + formatted[0] + ' (' + formatted.slice(1, 4);  // +7 (XXX
  }
  if (formatted.length >= 5) {
    result += ') ' + formatted.slice(4, 7); // +7 (XXX) XXX
  }
  if (formatted.length >= 8) {
    result += '-' + formatted.slice(7, 9);  // +7 (XXX) XXX-XX
  }
  if (formatted.length >= 10) {
    result += '-' + formatted.slice(9, 11);  // +7 (XXX) XXX-XX-XX
  }

  return result;
};


export function handlePhoneChange<T>(
  e: ChangeEvent<HTMLInputElement>,
  formInput: keyof T, setValue: (name: keyof T, value: any) => void, trigger: (name?: keyof T | (keyof T)[]) => Promise<boolean>
) {
  const formattedValue = formatPhoneNumber(e.target.value);  // Форматируем номер телефона
  setValue(formInput, formattedValue);  // Устанавливаем значение в react-hook-form
  trigger(formInput);  // Триггерим валидацию для конкретного поля
}

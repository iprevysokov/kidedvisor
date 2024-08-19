'use client'
import React, { useEffect, useState } from 'react'
import './AuthPage.scss';
import Input from '../Input/Input';
import Button from '../Button/Button';
import { useForm } from 'react-hook-form';
import { defaultRequiredMessage } from '@/src/lib/constants';

enum AuthState {
    TEL,
    EMAIL,
}

interface formData {
    email: string;
    tel: string;
}

export default function AuthPage() {
    const { register, handleSubmit, watch, formState: { errors, isValid }, trigger } = useForm<formData>({ mode: 'all' });
    const [authState, setAuthState] = useState<AuthState>(AuthState.TEL)

    function onNextClick() {
        setAuthState(AuthState.EMAIL)
    }

    function onSendCodeClick() {

    }

    useEffect(() => {

    }, [])

    return (
        <main className='authPage'>
            <form className='authPage__form'>
                {authState == AuthState.TEL && (
                    <>
                        <h2 className={'authPage__title'}>Введите номер телефона</h2>
                        <p className={'authPage__info'}>Доступ к данным будет только у нас.<br /> Будет использован как логин</p>
                        <Input placeholder='+7 (999) 999-99-99' type='tel' {...register('tel', {
                            required: { value: true, message: defaultRequiredMessage },
                            pattern: {
                                value: /^[+]?\d{1,2}\s?\(?\d{3}\)?\s?\d{3}-|\s?\d{2}-|\s?\d{2}$/,
                                message: "Некорректный номер телефона",
                            },
                        })} />
                        <Button onClick={onNextClick} title='Далее' additionalClass='authPage__button' disabled={Boolean(errors.tel)} />
                    </>
                )}
                {authState == AuthState.EMAIL && (
                    <>
                        <h2 className={'authPage__title'}>Получите код на ваш email</h2>
                        <p className={'authPage__info'}>Введите адрес электронной почты, мы отправим<br />
                            на нее код для подтверждения регистрации</p>
                        <Input placeholder='E-mail' type='email' {...register('email', { required: { value: true, message: defaultRequiredMessage } })} />
                        <Button onClick={onSendCodeClick} title='Выслать код' additionalClass='authPage__button' disabled={Boolean(errors.email)} />
                    </>
                )}
            </form>
        </main>
    )
}

'use client'
import React, { useState } from 'react'
import './AuthPage.scss';
import Input from '../Input/Input';
import Button from '../Button/Button';

enum AuthState {
    TEL,
    EMAIL,
}

export default function AuthPage() {
    const [authState, setAuthState] = useState<AuthState>(AuthState.TEL)

    function onNextClick() {
        setAuthState(AuthState.EMAIL)
    }

    function onSendCodeClick() {

    }

    return (
        <main className='authPage'>
            <form className='authPage__form'>
                {authState == AuthState.TEL && (
                    <>
                        <h2 className={'authPage__title'}>Введите номер телефона</h2>
                        <p className={'authPage__info'}>Доступ к данным будет только у нас.<br /> Будет использован как логин</p>
                        <Input placeholder='+7 (999) 999-99-99' type='tel' />
                        <Button onClick={onNextClick} title='Далее' additionalClass='authPage__button' />
                    </>)}
                {authState == AuthState.EMAIL && (
                    <>
                        <h2 className={'authPage__title'}>Получите код на ваш email</h2>
                        <p className={'authPage__info'}>Введите адрес электронной почты, мы отправим<br />
                            на нее код для подтверждения регистрации</p>
                        <Input placeholder='E-mail' type='email' />
                        <Button onClick={onSendCodeClick} title='Выслать код' additionalClass='authPage__button' />
                    </>)}
            </form>
        </main>
    )
}

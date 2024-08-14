'use client'
import React, { useState } from 'react'
import './AuthPage.scss';
import Input from '../Input/Input';
import Button from '../Button/Button';

enum AuthState {
    TEL,
    EMAIL,
    CODE
}

export default function AuthPage() {
    const [authState, setAuthState] = useState<AuthState>(AuthState.TEL)


    function onNextClick() {
        setAuthState(AuthState.EMAIL)
    }

    function onSendCodeClick() {
        setAuthState(AuthState.CODE)
    }

    return (
        <main>
            {authState == AuthState.TEL && (
                <>
                    <Input title='телефон' type='tel' />
                    <Button onClick={onNextClick} title='Далее' />
                </>)}
            {authState == AuthState.EMAIL && (
                <>
                    <Input title='емейл' type='email' />
                    <Button onClick={onSendCodeClick} title='Выслать код' />
                </>)}
            {authState == AuthState.CODE && (
                <>
                    <Input title='код' type='text' />
                    <Button onClick={onSendCodeClick} title='Отправить новый код' />
                </>)}
        </main>
    )
}

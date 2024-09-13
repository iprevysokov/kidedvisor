'use client'
import React, { useEffect, useRef, useState } from 'react';
import './AuthPage.scss';
import Input from '../Input/Input';
import Button from '../Button/Button';
import AuthWith_Button from '../AuthWith_Button/AuthWith_Button';
import { Controller, useForm } from 'react-hook-form';
import { defaultRequiredMessage } from '@/src/utils/constants';
//import AuthWith_Menu from '../AuthWith_Menu/AuthWIth_Menu';
import back_afford from '../../images/back_aff.svg';
import close_afford from '../../images/close_aff.svg';
import Image from 'next/image';
import { useRouter } from 'next/navigation';
import AuthWith_Menu from '../AuthWith_Menu/AuthWIth_Menu';
import logo from "../../images/Kidedvisor.svg";
import { useAppDispatch } from '@/src/utils/redux/store';
import { closePopup, openPopup } from '@/src/utils/redux/slices/appSlice';
import DefaultPopupElement from '../DefaultPopupElement/DefaultPopupElement';
import parsePhoneNumber from 'libphonenumber-js'
import { formatPhoneNumber } from '@/src/utils/utils';


enum AuthState {
    TEL,
    EMAIL,
}

interface formData {
    email: string;
    tel: string;
}

export default function AuthPage() {
    const { control, register, handleSubmit, watch, formState: { errors, isValid }, trigger, setValue, setFocus } = useForm<formData>({ mode: 'all' });
    const [phoneValue, setPhoneValue] = useState('');
    const [authState, setAuthState] = useState<AuthState>(AuthState.TEL)
    const [validOnStart, setValidOnStart] = useState<boolean>(false);
    const dispatch = useAppDispatch();
    const router = useRouter();

    function handlePhoneChange(e: React.ChangeEvent<HTMLInputElement>) {
        const formattedValue = formatPhoneNumber(e.target.value);
        setPhoneValue(formattedValue);
        setValue('tel', formattedValue);  // Устанавливаем значение в react-hook-form
        trigger()
    };

    function onNextClick() {
        setAuthState(AuthState.EMAIL)
    }

    function handleChangeEmail() {
        dispatch(closePopup())
        setFocus('email')
        trigger()
    }

    function sendCode() {
        dispatch(openPopup(
            <DefaultPopupElement heading='Проверьте почту' description='Пройдите по ссылке в почте для завершения регистрации'>
                <Button title={'Изменить почту'} outlined onClick={handleChangeEmail} />
            </DefaultPopupElement>
        ))
    }

    function openOAuthMenu() {
        dispatch(openPopup(<AuthWith_Menu />))
    }

    useEffect(() => {
        trigger()
        setValidOnStart(true)
        if (authState == AuthState.EMAIL) {
            setFocus('email')
        }
    }, [authState])

    const onSubmit = (data: formData) => {
        if (authState == AuthState.TEL) {
            onNextClick();
        }
        else if (authState == AuthState.EMAIL) {
            console.log(data)
            sendCode();
        }
    };

    return (
        <>
            <header className='auth_header'>
                <div className="top-container">
                    <button onClick={() => router.push('/')}>
                        <Image
                            className="back_afford"
                            src={back_afford.src}
                            width={14}
                            height={14}
                            alt="назад"
                        />
                    </button>
                    <button className="top-container__item"
                        onClick={() => router.back()}>
                        <Image
                            src={close_afford.src}
                            width={14}
                            height={14}
                            alt="закрыть"
                        />
                    </button>
                </div>
                <Image
                    className="header__logo"
                    src={logo.src}
                    height={61}
                    width={266}
                    alt="логотип"
                />
            </header>
            <main className='authPage'>
                <form className='authPage__form' onSubmit={handleSubmit(onSubmit)}>
                    {authState == AuthState.TEL && (
                        <>
                            <h2 className={'authPage__title'}>Введите номер телефона</h2>
                            <p className={'authPage__info'}>Доступ к данным будет только у нас.<br /> Будет использован как логин</p>
                            <Controller
                                name="tel"
                                control={control}
                                rules={{
                                    required: { value: true, message: 'Телефон обязателен' },
                                    // pattern: {
                                    //     value: /^[+]?\d{1,2}\s?\(?\d{3}\)?\s?\d{3}-?\s?\d{2}-?\s?\d{2}$/,
                                    //     message: 'Некорректный номер телефона',
                                    // },
                                    maxLength: 18,
                                }}
                                render={({ field }) => (
                                    <Input
                                        placeholder='+7 (999) 999-99-99'
                                        type='tel'

                                        {...field}
                                        value={phoneValue}
                                        onChange={handlePhoneChange}
                                    />
                                )}
                            />
                            <AuthWith_Button onClick={openOAuthMenu} />
                            <Button onClick={onNextClick} title='Далее' additionalClass='authPage__button' disabled={Boolean(errors.tel) || !validOnStart} />
                        </>
                    )}
                    {authState == AuthState.EMAIL && (
                        <>
                            <h2 className={'authPage__title'}>Получите ссылку для входа<br /> на ваш email</h2>
                            <p className={'authPage__info'}>Введите адрес электронной почты, мы отправим<br />
                                на нее код для подтверждения регистрации</p>
                            <Input placeholder='E-mail' type='email' {...register('email', { required: { value: true, message: defaultRequiredMessage } })} />
                            <Button type='submit' title='Отправить ссылку' additionalClass='authPage__button' disabled={Boolean(errors.email)} />
                        </>
                    )}
                </form>

            </main>
        </>
    )
}

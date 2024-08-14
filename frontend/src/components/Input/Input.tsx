import React from 'react'
import './Input.scss';

interface props {
    title: string;
    type?: 'text' | 'password' | 'tel' | 'email';
}

export default function Input({ title, type = 'text' }: props) {
    return (
        <div className={'auth_data_text_field'}>
            <input
                className={'auth_data_text_field__input'}
                type={type}
                placeholder={title}
            />
        </div>
    )
}

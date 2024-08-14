import React from 'react'
import './Input.scss';

interface props {
    title: string;
    type?: 'text' | 'password' | 'tel' | 'email';
    disabled?: boolean;
}

export default function Input({ title, type = 'text', disabled = false }: props) {
    return (
        <div className={'auth_data_text_field'}>
            <input
                className={'auth_data_text_field__input'}
                type={type}
                placeholder={title}
                disabled={disabled}
            />
        </div>
    )
}

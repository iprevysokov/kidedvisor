import React from 'react'
import './Input.scss';

interface props {
    placeholder: string;
    type?: 'text' | 'password' | 'tel' | 'email';
    additionalClass?: string;
}

export default function Input({ placeholder, type = 'text', additionalClass }: props) {
    return (
        <input
            className={`input ${additionalClass}`}
            type={type}
            placeholder={placeholder}
        />
    )
}

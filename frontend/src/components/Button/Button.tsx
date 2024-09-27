import React, { MouseEvent } from 'react'
import './Button.scss';

interface props {
    title: string;
    disabled?: boolean;
    onClick?: (e: MouseEvent<HTMLButtonElement>) => void;
    additionalClass?: string;
    type?: 'submit' | 'button';
    outlined?: boolean;
}

export default function Button({ title, disabled, onClick, additionalClass, type = 'button', outlined = false }: props) {
    return <button className={`${additionalClass} auth_button auth_button_${outlined ? 'outlined' : 'default'}`} disabled={disabled} onClick={onClick} type={type}>{title}</button>
}
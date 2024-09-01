import React, { MouseEvent } from 'react'
import './Button.scss';

interface props {
    title: string;
    disabled?: boolean;
    onClick?: (e: MouseEvent<HTMLButtonElement>) => void;
    additionalClass?: string;
    type?: 'submit' | 'button'
}

export default function Button({ title, disabled, onClick, additionalClass, type = 'button' }: props) {
    return <button className={`auth_button ${additionalClass}`} disabled={disabled} onClick={onClick} type={type}>{title}</button>
}
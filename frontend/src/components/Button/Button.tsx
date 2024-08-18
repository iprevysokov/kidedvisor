import React, { MouseEvent } from 'react'
import './Button.scss';

interface props {
    title: string;
    disabled?: boolean;
    onClick?: (e: MouseEvent<HTMLButtonElement>) => void;
    additionalClass?: string;
}

export default function Button({ title, disabled, onClick, additionalClass }: props) {
    return <button className={`auth_button ${additionalClass}`} disabled={disabled} onClick={onClick}>{title}</button>
}
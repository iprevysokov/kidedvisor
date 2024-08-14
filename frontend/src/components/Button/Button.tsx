import React, { MouseEvent } from 'react'
import './Button.scss';

interface props {
    title: string;
    disabled?: boolean;
    onClick?: (e: MouseEvent<HTMLButtonElement>) => void;
}

export default function Button({ title, disabled, onClick }: props) {
    return <button className={'auth_button'} disabled={disabled} onClick={onClick}>{title}</button>
}
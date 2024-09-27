import React, { MouseEvent } from 'react';
import './AuthWith_Button.scss';
import Image from 'next/image';
import Vector from '../../images/Vector_2.svg';

interface props {
    onClick: (e: MouseEvent<HTMLButtonElement>) => void;
}

export default function AuthWith({ onClick }: props) {
    return (
        <div className='authWithButton_container'>
            <button className='authWithButton_btn' type='button' onClick={onClick}>Авторизоваться с помощью
                <Image className='auth_vector' alt="Btn" src={Vector.src} width={8} height={13} />
            </button>
        </div>
    )
}
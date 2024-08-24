import React from 'react';
import './AuthWith_Button.scss';
import Image from 'next/image';
import Vector from '../../images/Vector_2.svg';

export default function AuthWith() {
    return (
        <div className='authWithButton_container'>
            <button className='authWithButton_btn'>Авторизоваться с помощью             
            <Image className='auth_vector' alt="Btn" src={Vector.src} width={8} height={13} />
            </button>
        </div>
    )
}
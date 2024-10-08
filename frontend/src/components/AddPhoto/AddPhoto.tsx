import React from "react";
import './AddPhoto.scss';
import Image from "next/image";
import Plus from '../../images/plusphoto_icon.svg'

interface props {
    additionalClass?: string; 
}

export default function AddPhoto({additionalClass}: props) {
    return (
        <button className={`${additionalClass} cards_button`} >
            <Image className='btn_icon' alt="Добавить" src={Plus.src} width={46} height={46} />
            <span className="btn_icon_title">Добавить фото</span>
        </button> 
    )
}
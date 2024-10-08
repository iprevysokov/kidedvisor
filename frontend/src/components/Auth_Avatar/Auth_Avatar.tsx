import React from "react";
import './Auth_Avatar.scss';
import Image from "next/image";
import Back_Aff from '../../images/back_aff.svg';
import Avatar from '../../images/Avatar_icon.svg';
import Button from "../Button/Button";
import AddPhoto from "../AddPhoto/AddPhoto";

export default function Auth_Avatar() {
    return (
        <>
            <div className="afford">
                <Image
                className="afford_image"
                src={Back_Aff}
                width={16}
                height={16}
                alt="Назад"
                />
            </div>
            <div className="auth_avatar_container">
                <h1>Фото профиля</h1>
                <Image
                className='auth_avatar_container_photo'
                src={Avatar}
                width={177}
                height={177}
                alt="Фото профиля"
                />
                <AddPhoto additionalClass="auth_avatar_container_btn"/>
            </div>  
            <Button additionalClass="btn" title="Сохранить"/> 
        </>
    )
}
import React from "react";
import './Auth_Personal_Cabinet.scss';
import Afford from "../Afford/Afford";
import Image from "next/image";
import Avatar from '../../images/Avatar_icon.svg';
import Input from "../Input/Input";
import Button from "../Button/Button";

export default function Auth_Personal_Cabinet() {
    return (
        <>
            <Afford />
            <div className="personal_cab">
                <h1 className="personal_cab_title">Профиль</h1>
                <div className="personal_cab_profile">
                    <Image
                    src={Avatar.src}
                    width={48}
                    height={48}
                    alt="Фото"
                    className="personal_cab_profile_avatar"
                    />
                    <div className="personal_cab_info">
                        <h1 className="personal_cab_info_name">Имя не заполнено</h1>
                        <button className="personal_cab_info_change_photo">
                            <span>Сменить фото</span>
                        </button>
                    </div>
                </div>
                <div className="profile_props">
                    <div>
                        <label className="profile_props_label" htmlFor="input">Введите имя</label>
                        <Input placeholder="Анастасия"/>
                    </div>
                    <div>
                        <label className="profile_props_label" htmlFor="input">Введите отчество</label>
                        <Input placeholder="Владимировна"/>
                    </div>
                    <div>
                        <label className="profile_props_label" htmlFor="input">Введите фамилию</label>
                        <Input placeholder="Иванова"/>
                    </div>
                    <div>
                        <label className="profile_props_label" htmlFor="input">Ваш номер телефона</label>
                        <Input type="tel" placeholder="8 (999) 999 99 99"/>
                    </div>
                    <div>
                        <label className="profile_props_label" htmlFor="input">Ваша почта</label>
                        <Input type="email" placeholder="person@mail.ru"/>
                    </div>
                </div>  
                <div className="personal_cab_btns">
                    <Button additionalClass="personal_cab_btns_btn" title="Сохранить изменения"/>
                    <Button additionalClass="personal_cab_btns_btn" outlined title="Выйти из профиля"/>
                    <Button additionalClass="personal_cab_btns_btn delete" outlined title="Удалить профиль" />
                </div>
            </div>
        </>
    )
}
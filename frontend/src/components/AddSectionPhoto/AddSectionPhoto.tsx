import React from "react";
import './AddSectionPhoto.scss';
import Button from "../Button/Button";
import Input from "../Input/Input";
import Image from "next/image";
import Plus from '../../images/plusphoto_icon.svg'
import Afford from "../Afford/Afford";

//Добавить попап

export default function AddSectionPhoto() {
    return (
        <>
        <Afford />
        <div className="add_photo">
            <div className="add_photo_container">
                <div className="add_photo_title">
                    Олимпийский
                </div>
                <Input additionalClass="add_photo_input" type="file" title=""/>
                <label htmlFor="add_photo_input">
                <button className="add_photo_button">
                    <Image className='btn_icon' alt="Добавить" src={Plus.src} width={46} height={46} />
                    <span className="btn_icon_title">Добавить фото</span>
                </button>
                </label>
                <Button title="Далее" additionalClass="button"/>
            </div>
        </div>
        </>
    )
}
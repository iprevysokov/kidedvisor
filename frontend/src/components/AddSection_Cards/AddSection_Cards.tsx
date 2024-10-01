import React from "react";
import './AddSection_Cards.scss';
import Button from "../Button/Button";
import Input from "../Input/Input";
import Image from "next/image";
import Plus from '../../images/plusphoto_icon.svg';
import Delete from '../../images/carddelete.svg';
import Afford from "../Afford/Afford";

export default function AddSection_Cards() {
    return (
        <div className="cards">
            <Afford />
            <div className="cards_container">
                <div className="cards_title">
                    Олимпийский
                </div> 
                <div className="cards_items">
                    <div id="card">
                        <button className="delete_btn">
                            <Image className="delete_icons" alt="Удалить" src={Delete.src} width={28} height={28}/>
                        </button>
                        <button className="cards_button">
                            <Image className='btn_icon' alt="Добавить" src={Plus.src} width={46} height={46} />
                            <span className="btn_icon_title">Добавить фото</span>
                        </button> 
                    </div>   
                </div>
                
                <Button title="Далее" additionalClass="button"/>
            </div>
        </div>
    )
}
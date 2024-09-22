'use client'
import React from "react";
import './SectionParams.scss';
import Input from "../Input/Input";
import Location from "../Map/Location";
import Button from "../Button/Button";
//import Map from '../Map/Map';

export default function SectionParams() {
    return (
        <div className="section_params">
            <div className="section_params_container">
                <h1 className="section_params_title">Олимпийский</h1>
                <div className="section_params_block">
                    <div className="section_params_label">
                        <span>Направление</span>
                    </div>
                    <Input additionalClass="dir_input" placeholder="Например, спорт"/>
                    <div className="section_params_label">
                        <span>Вид деятельности</span>
                    </div>
                    <Input additionalClass="dir_input" placeholder="Например, футбол"/>
                    <div className="section_params_label">
                        <span>Вид деятельности</span>
                    </div>
                    <Input additionalClass="dir_input" placeholder="Например, футбол"/>
                    <div className="section_params_label">
                        <span>Возраст</span>
                    </div>
                    <Input additionalClass="dir_input" placeholder="Введите возраст"/>
                    <div className="section_params_label">
                        <span>Адрес</span>
                    </div>
                    <Input additionalClass="dir_input" placeholder="Введите адрес"/>
                    <div className="section_params_label">
                        <span>Уточните место на карте</span>
                    </div>  
                    <div className="location">
                        <Location />
                    </div>
                </div>
            </div>
            <Button title="Далее" additionalClass="button"/>
        </div>
    )
}
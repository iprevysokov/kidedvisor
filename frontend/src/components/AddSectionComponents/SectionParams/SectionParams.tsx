'use client'
import React from "react";
import './SectionParams.scss';
import Input from "../../Input/Input";
import Location from "../../Map/Location";
import Button from "../../Button/Button";
//import Map from '../Map/Map';

interface props {
    onNextClick: () => void;
    heading: string;
}

export default function SectionParams({ onNextClick, heading }: props) {
    return (
        <div className="section_params">
            <div className="section_params_container">
                <h1 className="section_params_title">{heading}</h1>
                <div className="section_params_block">
                    <Input additionalClass="dir_input" placeholder="Например, спорт" label="Направление" />
                    <Input additionalClass="dir_input" placeholder="Например, футбол" label="Вид деятельности" />
                    <Input additionalClass="dir_input" placeholder="Введите возраст" label="Возраст" />
                    <Input additionalClass="dir_input" placeholder="Введите адрес" label="Адрес" />
                    <div className="section_params_label">
                        <span>Уточните место на карте</span>
                    </div>
                    <div className="location">
                        <Location />
                    </div>
                </div>
            </div>
            <Button title="Далее" additionalClass="button" onClick={onNextClick} />
        </div>
    )
}
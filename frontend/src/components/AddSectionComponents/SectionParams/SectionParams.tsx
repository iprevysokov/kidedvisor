'use client'
import React from "react";
import './SectionParams.scss';
import Input from "../../Input/Input";
import Location from "../../Map/Location";
import Button from "../../Button/Button";
import { IAddSectionFormInput } from "@/src/app/(customHeader)/section_owner/add/page";
import { UseFormGetValues, UseFormRegister, UseFormReturn } from "react-hook-form";
//import Map from '../Map/Map';

interface props {
    onNextClick: () => void;
    formMethods: UseFormReturn<IAddSectionFormInput>;
}

export default function SectionParams({ onNextClick, formMethods: { register, getValues } }: props) {
    return (
        <div className="section_params">
            <div className="section_params_container">
                <h1 className="section_params_title">{getValues('sectionName')}</h1>
                <div className="section_params_block">
                    <Input additionalClass="dir_input" placeholder="Например, спорт" label="Направление" {...register('sectionDirection', { required: true })} />
                    <Input additionalClass="dir_input" placeholder="Например, футбол" label="Вид деятельности" {...register('sectionType', { required: true })} />
                    <Input additionalClass="dir_input" placeholder="Введите возраст" label="Возраст" type="number" {...register('age', { required: true, max: 99, min: 0 })} />
                    <Input additionalClass="dir_input" placeholder="Введите адрес" label="Адрес" {...register('sectionAdress', { required: true })} />
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
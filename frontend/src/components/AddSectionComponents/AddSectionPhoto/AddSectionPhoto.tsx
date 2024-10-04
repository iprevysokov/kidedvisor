import React from "react";
import './AddSectionPhoto.scss';
import Image from "next/image";
import Plus from '../../../images/plusphoto_icon.svg'
import Input from "../../Input/Input";
import Button from "../../Button/Button";
import { IAddSectionFormInput } from "@/src/app/(customHeader)/section_owner/add/page";
import { UseFormReturn } from "react-hook-form";


//Добавить попап

interface props {
    onNextClick: () => void;
    formMethods: UseFormReturn<IAddSectionFormInput>;
}

export default function AddSectionPhoto({ onNextClick, formMethods: { getValues } }: props) {
    return (
        <div className="add_photo">
            <div className="add_photo_container">
                <div className="add_photo_title">
                    {getValues('sectionName')}
                </div>
                <Input additionalClass="add_photo_input" type="file" title="" />
                <label htmlFor="add_photo_input">
                    <button className="add_photo_button">
                        <Image className='btn_icon' alt="Добавить" src={Plus.src} width={46} height={46} />
                        <span className="btn_icon_title">Добавить фото</span>
                    </button>
                </label>
                <Button title="Далее" additionalClass="button" onClick={onNextClick} />
            </div>
        </div>
    )
}
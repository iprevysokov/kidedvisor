import React from 'react'
import Input from '../../Input/Input'
import Button from '../../Button/Button';

interface props {
    onNextClick: () => void;
    heading: string;
}

export default function SectionInfo({ onNextClick, heading }: props) {
    return (
        <div className="section_params">
            <div className="section_params_container">
                <h1 className="section_params_title">{heading}</h1>
                <div className="section_params_block">
                    <Input additionalClass="dir_input" placeholder="Например, с 9 до 18" label='Время работы' />
                    <Input additionalClass="dir_input" placeholder="Например, с Пн по Вс" label='Дни работы' />
                    <Input additionalClass="dir_input" placeholder="8 (499) 999-99-99" label='Номер телефона для связи' />
                    <Input additionalClass="dir_input" placeholder="section@mail.ru" label='Электронная почта' />
                    <Input additionalClass="dir_input" placeholder="8 (999) 999-99-99" label='WhatsApp' />
                    <Input additionalClass="dir_input" placeholder="Olimpiyskii.ru" label='Веб-сайт' />
                </div>
            </div>
            <Button title="Далее" additionalClass="button" onClick={onNextClick} />
        </div>
    )
}

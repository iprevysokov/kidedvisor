import React from 'react'
import Button from '../Button/Button';
import './TextFieldSection.scss';


interface props {
    onNextClick: () => void;
    heading: string;
    description: string;
    placeholder: string;
}

export default function TextFieldSection({ onNextClick, heading, description, placeholder }: props) {
    return (
        <div className="textfield_section">
            <div className="textfield_section_container">
                <h1 className="textfield_section_title">{heading}</h1>
                <div className="textfield_section_block">
                    <div className="textfield_section_label">
                        <span>{description}</span>
                    </div>
                    <textarea name='desc' id="desc_textarea" placeholder={placeholder}></textarea>
                </div>
            </div>
            <Button title="Далее" additionalClass="button" onClick={onNextClick} />
        </div>
    )
}

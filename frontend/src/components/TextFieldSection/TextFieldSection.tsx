import { UseFormRegisterReturn } from 'react-hook-form';
import Button from '../Button/Button';
import './TextFieldSection.scss';

interface props {
    onNextClick: () => void;
    heading: string;
    description: string;
    placeholder: string;
    register: UseFormRegisterReturn; 
}

export default function TextFieldSection({ onNextClick, heading, description, placeholder, register }: props) {
    return (
        <div className="textfield_section">
            <div className="textfield_section_container">
                <h1 className="textfield_section_title">{heading}</h1>
                <div className="textfield_section_block">
                    <div className="textfield_section_label">
                        <span>{description}</span>
                    </div>
                    <textarea id="desc_textarea" placeholder={placeholder} {...register} />
                </div>
            </div>
            <Button title="Далее" additionalClass="button" onClick={onNextClick} />
        </div>
    )
}

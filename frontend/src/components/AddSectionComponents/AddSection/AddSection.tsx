'use client'
import { IAddSectionFormInput } from '@/src/app/(customHeader)/section_owner/add/page';
import { UseFormReturn } from 'react-hook-form';
import Button from '../../Button/Button';
import Input from '../../Input/Input';
import './AddSection.scss';

interface props {
    onNextClick: () => void;
    formMethods: UseFormReturn<IAddSectionFormInput>;
}
export default function AddSection({ onNextClick, formMethods: { register } }: props) {
    return (
        <div className='add_section'>
            <div className='add_section_container'>
                <h1 className='add_section_title'>Новая секция</h1>
                <div className='section_name_container'>
                    <Input additionalClass='input_name' placeholder='Например, «Радуга»' type='text' label='Название секции' {...register('sectionName', { required: true })} />
                </div>
                <div className='section_desc_container'>
                    <div className='input_label_container'>
                        <span className='input_label'>Описание секции</span>
                    </div>
                    <textarea id="desc_textarea" placeholder='Введите описание'  {...register('sectionDescription', { required: true })}></textarea>
                </div>
            </div>
            <Button additionalClass='add_section_btn' title='Далее' onClick={onNextClick} />
        </div>
    )
}
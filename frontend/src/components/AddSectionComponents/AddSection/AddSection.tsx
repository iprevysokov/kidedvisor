'use client'
import React from 'react';
import './AddSection.scss'
import Input from '../../Input/Input';
import Button from '../../Button/Button';

interface props {
    onNextClick: () => void;
}

export default function AddSection({ onNextClick }: props) {
    return (
        <div className='add_section'>
            <div className='add_section_container'>
                <h1 className='add_section_title'>Новая секция</h1>
                <div className='section_name_container'>
                    <Input additionalClass='input_name' placeholder='Например, «Радуга»' type='text' label='Название секции' />
                </div>
                <div className='section_desc_container'>
                    <div className='input_label_container'>
                        <span className='input_label'>Описание секции</span>
                    </div>
                    <textarea name='desc' id="desc_textarea" placeholder='Введите описание'></textarea>
                </div>
            </div>
            <Button additionalClass='add_section_btn' title='Далее' onClick={onNextClick} />
        </div>
    )
}
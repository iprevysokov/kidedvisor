'use client'
import React from 'react';
import '../AddSection/AddSection.scss'
import Input from '../Input/Input';
import Button from '../Button/Button';
import Afford from '../Afford/Afford';

export default function AddSection() {
    return (
        <>
        <Afford />
        <div className='add_section'>
            <div className='add_section_container'>
                <h1 className='add_section_title'>Новая секция</h1>
                <div className='section_name_container'>
                    <div className='input_label_container'>
                        <span className='input_label'>Название секции</span>
                    </div>
                    <Input additionalClass='input_name' placeholder='Например, «Радуга»' type='text'/>
                </div>
                <div className='section_desc_container'>
                <div className='input_label_container'>
                        <span className='input_label'>Описание секции</span>
                </div>
                <textarea name='desc' id="desc_textarea" placeholder='Введите описание'></textarea>
                </div>
            </div>
            <Button additionalClass='add_section_btn' title='Далее'/>
        </div>
        </>
    )
}
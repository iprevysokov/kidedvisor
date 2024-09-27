'use client'
import React from 'react';
import './AddFinishPage.scss'
import Button from '../Button/Button';

export default function AddFinishPage() {
    return (
    <div className='add_finish_page'>
        <div className='add_finish_page_container'>
            <div className='add_finish_page_title'>
                <span>Спасибо!</span>
            </div>
            <div className='add_finish_page_desc'>
                <span>Ваши данные были отправлены на модерацию</span>
            </div>
            <Button title='ОК' additionalClass='button'/>
        </div>
    </div>
    )
}
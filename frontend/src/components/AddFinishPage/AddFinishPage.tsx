'use client'
import React from 'react';
import './AddFinishPage.scss'
import Button from '../Button/Button';
import Close from '../../images/close_aff.svg';
import Image from 'next/image';


export default function AddFinishPage() {
    return (
        <>  
            <div className='close_afford'>
                <Image
                src={Close.src}
                width={14}
                height={14}
                alt='Закрыть'
                />
            </div>
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
        </>
    )
}
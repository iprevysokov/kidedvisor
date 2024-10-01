import React from 'react';
import './AddSection_All_Info.scss';
import Image from 'next/image';
import Plus from '../../../images/plusphoto_icon.svg';
import Input from '../../Input/Input';
import Button from '../../Button/Button';

interface props {
    onNextClick: () => void;
}

export default function AddSection_All_Info({ onNextClick }: props) {
    return (
        <div className='all_info'>
            <div className='all_info_container'>
                <div className="all_info_photo">
                    <div className="all_info_photo_container">

                        <div className="all_info_photo_title">
                            Олимпийский
                        </div>

                        <Input additionalClass="all_info_photo_input" type="file" title="" />

                        <label htmlFor="all_info_photo_input">
                            <button className="all_info_photo_button">
                                <Image className='btn_icon' alt="Добавить" src={Plus.src} width={46} height={46} />
                                <span className="btn_icon_title">Добавить фото</span>
                            </button>
                        </label>
                    </div>
                </div>
                <div className='input_label_container'>
                    <span className='input_label'>Название секции</span>
                </div>
                <Input additionalClass='input_name' placeholder='Например, «Радуга»' type='text' />

                <div className='input_label_container'>
                    <span className='input_label'>Направление</span>
                </div>
                <Input additionalClass='input_name' placeholder='Направление' type='text' />

                <div className='input_label_container'>
                    <span className='input_label'>Вид деятельности</span>
                </div>
                <Input additionalClass='input_name' placeholder='Вид деятельности' type='text' />

                <div className='input_label_container'>
                    <span className='input_label'>Адрес</span>
                </div>
                <Input additionalClass='input_name' placeholder='Адрес' type='text' />

                <div className='input_label_container'>
                    <span className='input_label'>Возраст</span>
                </div>
                <Input additionalClass='input_name' placeholder='Возраст' type='text' />

                <div className='input_label_container'>
                    <span className='input_label'>Контакты</span>
                </div>
                <Input additionalClass='input_name' placeholder='Контакты' type='text' />

                <div className='input_label_container'>
                    <span className='input_label'>Время работы</span>
                </div>
                <Input additionalClass='input_name' placeholder='Время работы' type='text' />

                <div className='input_label_container'>
                    <span className='input_label'>Дни работы</span>
                </div>
                <Input additionalClass='input_name' placeholder='Дни работы' type='text' />

                <div className='input_label_container'>
                    <span className='input_label'>Абонементы</span>
                </div>
                <textarea name='desc' className="textarea_season_ticket" placeholder='Абонементы'></textarea>

                <div className='input_label_container'>
                    <span className='input_label'>Расписание</span>
                </div>
                <textarea name='desc' className="textarea_shedule" placeholder='Расписание'></textarea>

                <div className='input_label_container'>
                    <span className='input_label'>Описание секции</span>
                </div>
                <textarea name='desc' className="textarea_desc" placeholder='Описание секции'></textarea>
                <Button title="Отправить" additionalClass="button" onClick={onNextClick} />
            </div>
        </div>
    )
}
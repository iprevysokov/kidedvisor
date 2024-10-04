import { IAddSectionFormInput } from '@/src/app/(customHeader)/section_owner/add/page';
import Image from 'next/image';
import { Controller, UseFormReturn } from 'react-hook-form';
import Plus from '../../../images/plusphoto_icon.svg';
import Button from '../../Button/Button';
import Input from '../../Input/Input';
import './AddSection_All_Info.scss';
import { handlePhoneChange } from '@/src/utils/utils';
import { ChangeEvent } from 'react';

interface props {
    onNextClick: () => void;
    formMethods: UseFormReturn<IAddSectionFormInput>;
}

export default function AddSection_All_Info({ onNextClick, formMethods: { getValues, register, setValue, trigger, control } }: props) {
    return (
        <div className='all_info'>
            <div className='all_info_container'>
                <div className="all_info_photo">
                    <div className="all_info_photo_container">

                        <div className="all_info_photo_title">
                            {getValues('sectionName')}
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
                <Input additionalClass='input_name' placeholder='Например, «Радуга»' type='text' label='Название секции' {...register('sectionName', { required: true })} />

                <Input additionalClass="dir_input" placeholder="Например, спорт" label="Направление" {...register('sectionDirection', { required: true })} />
                <Input additionalClass="dir_input" placeholder="Например, футбол" label="Вид деятельности" {...register('sectionType', { required: true })} />
                <Input additionalClass="dir_input" placeholder="Введите возраст" label="Возраст" type="number" {...register('age', { required: true, max: 99, min: 0 })} />
                <Input additionalClass="dir_input" placeholder="Введите адрес" label="Адрес" {...register('sectionAdress', { required: true })} />
                <Input additionalClass="dir_input" placeholder="Например, с 9 до 18" label='Время работы' {...register('workTime', { required: true })} />
                <Input additionalClass="dir_input" placeholder="Например, с Пн по Вс" label='Дни работы' {...register('workDays', { required: true })} />
                <Controller
                    name="contactNumber"
                    control={control}
                    rules={{
                        required: { value: true, message: 'Телефон обязателен' },
                        maxLength: 18,
                    }}
                    render={({ field }) => (
                        <Input
                            placeholder='+7 (999) 999-99-99'
                            label='Номер телефона для связи'
                            type='tel'

                            {...field}

                            onChange={(e: ChangeEvent<HTMLInputElement>) => handlePhoneChange<IAddSectionFormInput>(
                                e,
                                // setContactNumber,  // Локальный сеттер состояния для номера телефона
                                'contactNumber',  // Имя поля для формы
                                setValue,  // Функция из useForm для установки значения
                                trigger  // Функция из useForm для триггера валидации
                            )}
                        />
                    )}
                />
                <Input additionalClass="dir_input" placeholder="section@mail.ru" label='Электронная почта' type='email' {...register('contactEmail', { required: true })} />
                <Controller
                    name="contactWhatsApp"
                    control={control}
                    rules={{
                        required: { value: true, message: 'Телефон обязателен' },
                        maxLength: 18,
                    }}
                    render={({ field }) => (
                        <Input
                            placeholder='+7 (999) 999-99-99'
                            label='WhatsApp'
                            type='tel'

                            {...field}

                            onChange={(e: ChangeEvent<HTMLInputElement>) => handlePhoneChange<IAddSectionFormInput>(
                                e,
                                // setContactNumber,  // Локальный сеттер состояния для номера телефона
                                'contactWhatsApp',  // Имя поля для формы
                                setValue,  // Функция из useForm для установки значения
                                trigger  // Функция из useForm для триггера валидации
                            )}
                        />
                    )}
                />
                <Input additionalClass="dir_input" placeholder="Olimpiyskii.ru" label='Веб-сайт' {...register('website', { required: true })} />
                <div className="textfield_section_block">
                    <div className="textfield_section_label">
                        <span>{'Абонементы'}</span>
                    </div>
                    <textarea id="desc_textarea" placeholder={'Информация об абонементах'} {...register('abonements')} />
                </div>
                <div className="textfield_section_block">
                    <div className="textfield_section_label">
                        <span>{'Расписание'}</span>
                    </div>
                    <textarea id="desc_textarea" placeholder={'Информация о расписании'} {...register('abonements')} />
                </div>
                <div className="textfield_section_block">
                    <div className="textfield_section_label">
                        <span>{'Описание секции'}</span>
                    </div>
                    <textarea id="desc_textarea" placeholder={'Описание секции'} {...register('sectionDescription')} />
                </div>
            </div>
            <Button title="Отправить" additionalClass="button" onClick={onNextClick} />
        </div>
    )
}
import React, { useState, ChangeEvent } from 'react'
import Input from '../../Input/Input'
import Button from '../../Button/Button';
import { IAddSectionFormInput } from '@/src/app/(customHeader)/section_owner/add/page';
import { Control, Controller, UseFormRegister } from 'react-hook-form';
import { formatPhoneNumber } from '@/src/utils/utils';

interface props {
    onNextClick: () => void;
    heading: string;
    register: UseFormRegister<IAddSectionFormInput>;
    control: Control<IAddSectionFormInput>;
    setValue: (name: keyof IAddSectionFormInput, value: any, config?: object) => void;
    trigger: (name?: keyof IAddSectionFormInput | (keyof IAddSectionFormInput)[]) => Promise<boolean>;
}

export default function SectionInfo({ onNextClick, heading, register, control, setValue, trigger }: props) {
    // const [contactNumber, setContactNumber] = useState('');


    function handlePhoneChange<T>(
        e: ChangeEvent<HTMLInputElement>,
        // setter: (value: string) => void,
        formInput: keyof T, setValue: (name: keyof T, value: any) => void, trigger: (name?: keyof T | (keyof T)[]) => Promise<boolean>
    ) {
        const formattedValue = formatPhoneNumber(e.target.value);  // Форматируем номер телефона
        // setter(formattedValue);  // Обновляем локальное состояние
        setValue(formInput, formattedValue);  // Устанавливаем значение в react-hook-form
        trigger(formInput);  // Триггерим валидацию для конкретного поля
    }

    return (
        <div className="section_params">
            <div className="section_params_container">
                <h1 className="section_params_title">{heading}</h1>
                <div className="section_params_block">
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
                </div>
            </div>
            <Button title="Далее" additionalClass="button" onClick={onNextClick} />
        </div>
    )
}

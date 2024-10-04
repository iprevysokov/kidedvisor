import { IAddSectionFormInput } from '@/src/app/(customHeader)/section_owner/add/page';
import { formatPhoneNumber, handlePhoneChange } from '@/src/utils/utils';
import { ChangeEvent } from 'react';
import { Controller, UseFormReturn } from 'react-hook-form';
import Button from '../../Button/Button';
import Input from '../../Input/Input';

interface props {
    onNextClick: () => void;
    formMethods: UseFormReturn<IAddSectionFormInput>;
}

export default function SectionInfo({ onNextClick, formMethods: { register, control, setValue, getValues, trigger } }: props) {

    return (
        <div className="section_params">
            <div className="section_params_container">
                <h1 className="section_params_title">{getValues('sectionName')}</h1>
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

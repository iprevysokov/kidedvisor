'use client'
import AddSection from "@/src/components/AddSectionComponents/AddSection/AddSection";
import AddSection_All_Info from "@/src/components/AddSectionComponents/AddSection_All_Info/AddSection_All_Info";
import AddSection_Cards from "@/src/components/AddSectionComponents/AddSection_Cards/AddSection_Cards";
import AddSectionPhoto from "@/src/components/AddSectionComponents/AddSectionPhoto/AddSectionPhoto";
import SectionInfo from "@/src/components/AddSectionComponents/SectionInfo/SectionInfo";
import SectionParams from "@/src/components/AddSectionComponents/SectionParams/SectionParams";
import TextFieldSection from "@/src/components/TextFieldSection/TextFieldSection";
import { useState } from "react";
import { useForm } from "react-hook-form";

enum FormState {
    nameAndDescription,
    infoAndLocation,
    workInfo,
    abonements,
    timetable,
    mainPhoto,
    photos,
    allInfo,
}

export interface IAddSectionFormInput {
    sectionName: string;
    sectionDescription: string;
    sectionDirection: string;
    sectionType: string;
    age: number;
    sectionAdress: string;
    workTime: string;
    workDays: string;
    contactNumber: string;
    contactEmail: string;
    contactWhatsApp: string;
    website: string;
    abonements: string;
    timetable: string;
}

export default function Add() {
    const [formState, setFormState] = useState<FormState>(FormState.nameAndDescription);
    const validation = useForm<IAddSectionFormInput>();
    const { register, handleSubmit, formState: { errors }, getValues, control, setValue, trigger } = validation;

    function handleNextClick() {
        setFormState((prev) => prev += 1);
    }

    return (
        <form>
            {formState == FormState.nameAndDescription && <AddSection onNextClick={handleNextClick} formMethods={validation} />}
            {formState == FormState.infoAndLocation && <SectionParams onNextClick={handleNextClick} formMethods={validation} />}
            {formState == FormState.workInfo && <SectionInfo onNextClick={handleNextClick} formMethods={validation} />}
            {formState == FormState.abonements &&
                <TextFieldSection
                    onNextClick={handleNextClick}
                    heading="Абонемент"
                    description="Какие абонементы доступны"
                    placeholder="Информация об абонементах"
                    register={register('abonements')}
                />
            }
            {formState == FormState.timetable &&
                <TextFieldSection
                    onNextClick={handleNextClick}
                    heading="Расписание"
                    description="Укажите информацию о расписании доступных групп"
                    placeholder="Информация о расписании"
                    register={register('timetable')}
                />
            }
            {formState == FormState.mainPhoto && <AddSectionPhoto onNextClick={handleNextClick} formMethods={validation} />}
            {formState == FormState.photos && <AddSection_Cards onNextClick={handleNextClick} formMethods={validation} />}
            {formState == FormState.allInfo && <AddSection_All_Info onNextClick={handleNextClick} formMethods={validation} />}
        </form>
    )
}
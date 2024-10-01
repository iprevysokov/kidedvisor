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

interface IFormInput {
    sectionName: string;
    sectionDescription: string;
}

export default function Add() {
    const [formState, setFormState] = useState<FormState>(FormState.nameAndDescription);
    const { register, handleSubmit, formState: { errors }, getValues } = useForm<IFormInput>();

    function handleNextClick() {
        setFormState((prev) => prev += 1);
    }

    return (
        <form>
            {formState == FormState.nameAndDescription && <AddSection onNextClick={handleNextClick} />}
            {formState == FormState.infoAndLocation && <SectionParams onNextClick={handleNextClick} heading={getValues('sectionName')} />}
            {formState == FormState.workInfo && <SectionInfo onNextClick={handleNextClick} heading={getValues('sectionName')} />}
            {formState == FormState.abonements &&
                <TextFieldSection
                    onNextClick={handleNextClick}
                    heading="Абонемент"
                    description="Какие абонементы доступны"
                    placeholder="Информация об абонементах" />}
            {formState == FormState.timetable &&
                <TextFieldSection
                    onNextClick={handleNextClick}
                    heading="Расписание"
                    description="Укажите информацию о расписании доступных групп"
                    placeholder="Информация о расписании" />}
            {formState == FormState.mainPhoto && <AddSectionPhoto onNextClick={handleNextClick} />}
            {formState == FormState.photos && <AddSection_Cards onNextClick={handleNextClick} />}
            {formState == FormState.allInfo && <AddSection_All_Info onNextClick={handleNextClick} />}
        </form>
    )
}
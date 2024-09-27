'use client'
import AddSection from "@/src/components/AddSection/AddSection";
import SeasonTicket from "@/src/components/SeasonTicket/SeasonTicket";
import SectionParams from "@/src/components/SectionParams/SectionParams";
import { useState } from "react";

enum FormState {
    nameAndDescription,
    infoAndLocation,
    workInfo,
    abonements,
    raspisanie,
    mainPhoto,
    photos,
    allInfo,
}

export default function Add() {
    const [formState, setFormState] = useState<FormState>(FormState.nameAndDescription);

    function handleNextClick() {
        setFormState((prev) => prev += 1);
    }

    return (
        <>
            {formState == FormState.nameAndDescription && <AddSection onNextClick={handleNextClick} />}
            {formState == FormState.infoAndLocation && <SectionParams onNextClick={handleNextClick} />}
            {formState == FormState.workInfo && <SectionParams onNextClick={handleNextClick} />}
            {formState == FormState.abonements && <SeasonTicket />}
        </>
    )
}
import React from "react";
import './SectionPage_message.scss';
import Button from "../Button/Button";
import Image from "next/image";
import close_afford from '../../images/close_aff.svg'


interface props {
    title: string;
    text: string;
    additionalClass?: string;
}

export default function SectionPage_message({title, text, additionalClass}: props) {
    return (
        <>
        <button className="btn_close">
                <Image className='close_afford' alt='закрыть' src={close_afford.src} width={14} height={14}/>
        </button>
        <div className="section_page_message">
            <h1>{title}</h1>
            <h2>{text}</h2>
            <Button title="Ок" additionalClass="message_btn"/>
        </div>
        </>
    )
}
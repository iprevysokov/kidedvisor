import React from "react";
import './ContactUs.scss';
import Afford from "../Afford/Afford";
import Button from "../Button/Button";

export default function ContactUs() {
    return (
        <>
        <Afford />
        <div className="contact_us">
            <div className="contact_us_container">
                <span>Напишите нам</span>
                <div className="contact_us_message">
                    <textarea placeholder="Текст сообщения" className="contact_us_textarea">
                    </textarea>
                </div>
            </div>
            <Button additionalClass="btn" title="Отправить"/>
        </div>
        </>
    )
}
import React from "react";
import './ContactUs.scss';
import Afford from "../Afford/Afford";

export default function ContactUs() {
    return (
        <div className="contact_us">
            <Afford />
            <div className="contact_us_container">
                <span>Напишите нам</span>
                <div className="contact_us_message">
                    <textarea placeholder="Текст сообщения" className="contact_us_textarea">
                    </textarea>
                </div>
            </div>
        </div>
    )
}
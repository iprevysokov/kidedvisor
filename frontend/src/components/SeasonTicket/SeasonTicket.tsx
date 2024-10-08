import React from "react";
import './SeasonTicket.scss';
import Button from "../Button/Button";
import Afford from "../Afford/Afford";

interface props {
    additionalClass?: string;
    title: string;
    label: string;
    placeholder: string;
}

export default function SeasonTicket({additionalClass, title, label, placeholder}: props) {
    return (
        <>
        <Afford />
        <div className={`season_ticket ${additionalClass}`}>
            <div className="season_ticket_container">
                <h1 className="season_ticket_title">{title}</h1>
                <div className="season_ticket_block">
                    <div className="season_ticket_label">
                        <span>{label}</span>
                    </div>
                    <textarea name='desc' id="desc_textarea" placeholder={placeholder}></textarea>
                </div>
            </div>
            <Button title="Далее" additionalClass="button"/>
        </div>
        </>
    )
}
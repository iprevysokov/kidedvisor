import React from "react";
import './SeasonTicket.scss';
import Button from "../Button/Button";

export default function SeasonTicket() {
    return (
        <div className="season_ticket">
            <div className="season_ticket_container">
                <h1 className="season_ticket_title">Абонемент</h1>
                <div className="season_ticket_block">
                    <div className="season_ticket_label">
                        <span>Какие абонементы доступны</span>
                    </div>
                    <textarea name='desc' id="desc_textarea" placeholder='Какие абонементы доступны'></textarea>
                </div>
            </div>
            <Button title="Далее" additionalClass="button"/>
        </div>
    )
}
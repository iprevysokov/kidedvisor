import React from "react";
import './SeasonTicket.scss';
import Button from "../Button/Button";

export default function Shedule() {
    return (
        <div className="season_ticket">
            <div className="season_ticket_container">
                <h1 className="season_ticket_title">Расписание</h1>
                <div className="season_ticket_block">
                    <div className="season_ticket_label">
                        <span>Укажите информацию о расписании доступных групп</span>
                    </div>
                    <textarea name='desc' id="desc_textarea" placeholder='Информация о расписании'></textarea>
                </div>
            </div>
            <Button title="Далее" additionalClass="button"/>
        </div>
    )
}
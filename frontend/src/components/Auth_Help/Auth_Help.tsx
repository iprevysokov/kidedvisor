import React from "react";
import './Auth_Help.scss';
import Afford from "../Afford/Afford";
import Button from "../Button/Button";

export default function Auth_Help() {
    return (
        <>
            <Afford />
            <div className="auth_help">
                <h1>Помощь</h1>
                <h2>Задайте вопрос в свободной форме</h2>
                <textarea placeholder="Текст вашего обращения" />
            </div>
            <Button additionalClass="btn" title="Отправить"/>
        </>
    )
}
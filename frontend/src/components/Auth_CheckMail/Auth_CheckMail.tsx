import React from "react";
import './Auth_CheckMail.scss';
import Button from "../Button/Button";

export default function Auth_CheckMail() {
    return (
        <div className="check_mail">
            <h1>Проверьте почту</h1>
            <h2>Пройдите по ссылке в почте для завершения регистрации</h2>
            <Button additionalClass="btn" outlined title="Изменить почту"/>
        </div>
    )
}
import React from "react";
import './ToggleSwitch.scss';

export default function ToggleSwitch() {
    return (
        <div className="toggleSwitch">
            <label className="switch">
            <input type="checkbox"/>
            <span className="slider round"></span>
            </label>
        </div>
    )
}
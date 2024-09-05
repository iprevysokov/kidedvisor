import React, { ReactNode } from 'react'
import './DefaultPopupElement.scss';

interface props {
    heading: string;
    description: string;
    children?: ReactNode
}

export default function DefaultPopupElement({ children, heading, description }: props) {
    return (
        <div className='default_popup'>
            <div className='default_popup__container'>
                <h2 className='default_popup__heading'>{heading}</h2>
                <p className='default_popup__info'>{description}</p>
            </div>
            {children}
        </div>
    )
}

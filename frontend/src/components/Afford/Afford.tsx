import React from "react";
import './Afford.scss';
import Image from "next/image";
import close_afford from '../../images/close_aff.svg';
import back_afford from '../../images/back_aff.svg';

export default function Afford() {
    return (
        <div className='afford'>
            <button>
                <Image className='back_afford' alt='назад' src={back_afford.src} width={14} height={14}/>
            </button>
            <button>
                <Image className='close_afford' alt='закрыть' src={close_afford.src} width={14} height={14}/>
            </button>
        </div>
    )
}
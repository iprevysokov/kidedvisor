import React from "react";
import './CardProgress.scss';
import Image from "next/image";
import Done from '@/src/images/done_icon.svg';

interface props {
    title: string;
    //disabled?: boolean;
    //onClick?: (e: MouseEvent<HTMLButtonElement>) => void;
    additionalClass?: string;
}


export default function CardProgress({title, additionalClass}: props) {
    return (
        <div className= {`card_progress ${additionalClass}`}>
            <span>{title}</span>
            <Image 
                id="card_progress_icon"
                src={Done.src}
                width={28}
                height={28}
                alt={title}
            />
        </div>
    )
}
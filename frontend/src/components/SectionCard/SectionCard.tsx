import { ISectionCard } from '@/src/utils/types'
import Image from 'next/image';
import React from 'react';
import './SectionCard.scss';

interface props {
    card: ISectionCard;
}

export default function SectionCard({ card }: props) {
    return (
        <div className='SectionCard'>
            <Image src={card.image} alt={'Фотография секции'} width={1920} height={1080} className='SectionCard__image' />
            <p className='SectionCard__rating'>{card.rating}</p>
            <h3 className='SectionCard__name'>{card.name}</h3>
        </div>
    )
}

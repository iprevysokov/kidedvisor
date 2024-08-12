'use client'
import React from 'react'
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import { ISectionCard } from '@/src/lib/types';
import mockImage from '../../images/mockSectionImage.png';
import SectionCard from '../SectionCard/SectionCard';
import './SliderSection.scss';

const mockData: ISectionCard[] = [
    { name: 'Школа искусств «Тутти»', image: mockImage.src, rating: 4.9 },
    { name: 'Школа искусств «Тутти»', image: mockImage.src, rating: 4.9 },
    { name: 'Школа искусств «Тутти»', image: mockImage.src, rating: 4.9 },
    { name: 'Школа искусств «Тутти»', image: mockImage.src, rating: 4.9 },
    { name: 'Школа искусств «Тутти»', image: mockImage.src, rating: 4.9 },
    { name: 'Школа искусств «Тутти»', image: mockImage.src, rating: 4.9 },
]

interface props {
    heading: string;
}

export default function SliderSection({ heading }: props) {
    return (
        <section className='SliderSection'>
            <h3 className='SliderSection__heading'>{heading}</h3>
            <Swiper
                navigation
                autoplay={true}
                slidesPerView={3}
            >
                <SwiperSlide>
                    {mockData.map((item, index) => (<SectionCard card={item} key={index} />))}
                </SwiperSlide>
            </Swiper>
        </section>
    )
}

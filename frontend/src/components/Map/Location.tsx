'use client'
import React, { useEffect, useState } from 'react';
import { YMaps, Map, Placemark } from '@pbe/react-yandex-maps';
import './Location.scss'

export default function Location() {
    const [coordinates, setCoordinates] = useState<[number, number]>([55.75, 37.57]);

    useEffect(() => {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                (position) => {
                    const { latitude, longitude } = position.coords;
                    setCoordinates([latitude, longitude]); // Устанавливаем координаты пользователя
                },
                () => {
                    console.error("Ошибка получения местоположения");
                }
            );
        } else {
            console.error("Geolocation не поддерживается");
        }
    }, []);

    function handleMapClick(event: any) {
        const coords = event.get('coords');
        setCoordinates(coords);
    }

    return (
        <div className='map_container'>
            <YMaps>
                <Map className='map_container_map'
                    defaultState={{ center: coordinates, zoom: 9 }} // Используем координаты пользователя
                    onClick={handleMapClick}
                >
                    <Placemark geometry={coordinates} />
                </Map>
            </YMaps>
        </div>
    )

};
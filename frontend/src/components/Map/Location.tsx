import React from 'react';
import { YMaps, Map } from '@pbe/react-yandex-maps';
import './Location.scss'

export default function Location() {
    return (
        <div className='map_container'>
            <YMaps >
                <div>
                <Map defaultState={{ center: [55.75, 37.57], zoom: 9 }} />
                </div>
            </YMaps>
        </div>
    )
        
};
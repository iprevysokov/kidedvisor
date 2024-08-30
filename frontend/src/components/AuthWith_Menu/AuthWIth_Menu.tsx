import React, { MouseEvent, useEffect, useState } from 'react';
import './AuthWith_Menu.scss';
import Image from 'next/image';
import vkLogo from '../../images/vk.svg';
import yaLogo from '../../images/yandex.svg';

interface props {
    onCloseMenu: () => void;
}

export default function AuthWith_Menu({ onCloseMenu }: props) {
    const [additionalClass, setAdditionalClass] = useState<'opened' | 'closed' | ''>('');


    function handleCloseMenu(e: MouseEvent<HTMLDivElement>) {
        if (e.target == e.currentTarget) {
            setAdditionalClass('closed')
            setTimeout(() => onCloseMenu(), 300)

        }
    }

    useEffect(() => {
        setAdditionalClass('opened')
    }, [])

    return (
        <div className={`authWith_menu_container authWith_menu_container_${additionalClass}`} onClick={handleCloseMenu}>
            <ul className={`authWith_menu_ul authWith_menu_ul_${additionalClass}`}>
                <li id='auth_vk'>
                    <a href='#'> Авторизоваться с помощью <Image className='auth_vk_img' alt='vk' src={vkLogo.src} width={40} height={40} /></a>
                </li>
                <hr id='auth_hr'></hr>
                <li id='auth_yandex'>
                    <a href='#'> Авторизоваться с помощью <Image className='auth_ya_img' alt='yandex' src={yaLogo.src} width={40} height={40} /></a>
                </li>
            </ul>
        </div>
    )
}
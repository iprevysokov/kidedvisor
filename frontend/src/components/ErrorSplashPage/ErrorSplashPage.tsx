'use client'
import React, { ReactNode } from 'react';
import Image from 'next/image';
import '../ErrorSplashPage/ErrorSplashPage.scss';
import close_afford from '../../images/close_aff.svg';
import { useRouter } from 'next/navigation';

interface props {
    children: ReactNode
}

export default function ErrorSplashPage({ children }: props) {
    const router = useRouter();

    function navigateToMain() {
        router.push('/')
    }
    return (
        <div className='splash'>
            <div className='afford'>
                <button onClick={navigateToMain}>
                    <Image className='close_afford' alt='закрыть' src={close_afford.src} width={14} height={14} />
                </button>
            </div>
            <div className='splash_container'>
                {children}
            </div>
        </div>
    )
}
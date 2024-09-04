import React from 'react';
import Image from 'next/image';
import errorImg from '../../images/err_img.svg';
import splashImg from '../../images/splash_img.svg';
import '../ErrorSplashPage/ErrorSplashPage.scss';
import close_afford from '../../images/close_aff.svg'

export default function ErrorSplashPage() {
    /*return (
        <div className='error'>
            <div className='afford'>
                <button>
                    <Image className='close_afford' alt='закрыть' src={close_afford.src} width={14} height={14}/>
                </button>
            </div>
            <div className='error_container'>
                <Image className='err_img' alt='Ошибка 404' src={errorImg.src} width={240} height={135} />
                <h1 className='err_title'>Упс!</h1>
                <p className='err_text'>Такая страница не существует</p>
                <button className='err_btn'>Ок</button>
            </div>
        </div>
    )*/
        return (
            <div className='splash'>
                <div className='afford'>
                    <button>
                        <Image className='close_afford' alt='закрыть' src={close_afford.src} width={14} height={14}/>
                    </button>
                </div>
                <div className='splash_container'>
                    <Image className='splash_img' alt='Ошибка 404' src={splashImg.src} width={240} height={135} />
                    <h1 className='splash_title'>Спасибо за регистрацию!</h1>
                    <p className='splash_text'>У нас ремонт, скоро здесь будет
                    очень крутое приложение. Оставайтесь с нами, чтобы быть в курсе новостей</p>
                    <button className='splash_btn'>Ок</button>
                </div>
            </div>
        )
}
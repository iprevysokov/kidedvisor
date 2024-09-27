'use client'
import ErrorSplashPage from "@/src/components/ErrorSplashPage/ErrorSplashPage";
import Image from "next/image";
import { useRouter } from "next/navigation";
import splashImg from '../../../images/splash_img.svg';

export default function ErrSplash() {
    const router = useRouter();
    function navigateToMain() {
        router.push('/')
    }
    return (
        <ErrorSplashPage>
            <Image className='splash_img' alt='Ошибка 404' src={splashImg.src} width={240} height={135} />
            <h1 className='splash_title'>Спасибо за регистрацию!</h1>
            <p className='splash_text'>У нас ремонт, скоро здесь будет
                очень крутое приложение. Оставайтесь с нами, чтобы быть в курсе новостей</p>
            <button className='splash_btn' onClick={navigateToMain}>Ок</button>
        </ErrorSplashPage>)
}
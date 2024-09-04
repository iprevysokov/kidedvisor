import Link from "next/link";
import styles from "./page.module.scss";
import ErrorSplashPage from "../components/ErrorSplashPage/ErrorSplashPage";
import errorImg from '../images/err_img.svg';
import Image from "next/image";
export default function NotFound() {
    return (
        <ErrorSplashPage>
            <Image className='err_img' alt='Ошибка 404' src={errorImg.src} width={240} height={135} />
            <h1 className='err_title'>Упс!</h1>
            <p className='err_text'>Такая страница не существует</p>
            <button className='err_btn'>Ок</button>
        </ErrorSplashPage>
    );
}

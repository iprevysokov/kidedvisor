import React from 'react'
import './BurgerMenu.scss';
import logo from "../../images/Kidedvisor.svg";
import back_afford from '../../images/back_aff.svg';
import close_afford from '../../images/close_aff.svg';
import burgerImage from '../../images/burger.svg';
import Image from "next/image";
import { useAppDispatch, useAppSelector } from "@/src/utils/redux/store";
import { switchBurgerMenu } from "@/src/utils/redux/slices/appSlice";
import mockAvatar from '../../images/mockAvatar.svg';
import rightArrow from '../../images/rightArrow.svg';
export default function BurgerMenu() {
    const dispatch = useAppDispatch();

    function handleCloseBurgerMenu() {
        dispatch(switchBurgerMenu());
    }

    return (
        <section className='burgerMenu'>
            <div className="top-container">
                <button>
                    <Image
                        className="back_afford"
                        src={back_afford.src}
                        width={14}
                        height={14}
                        alt="назад"
                    />
                </button>
                <button className="top-container__item">
                    <Image
                        src={close_afford.src}
                        width={14}
                        height={14}
                        alt="закрыть"
                        onClick={handleCloseBurgerMenu}
                    />
                </button>
            </div>
            <div className='info-container'>
                <h2 className='info-container__heading'>Мои данные</h2>
                <div className='info-container__sub-container'>
                    <Image src={mockAvatar.src} width={50} height={50} alt='Аватар пользователя' />
                    <div className='info-container__info'>
                        <h3 className='info-container__user-name'>Имя пользователя</h3>
                        <div className='info-container__label'>Личный кабинет <Image src={rightArrow.src} width={5} height={15} alt='стрелочка' /></div>
                    </div>
                </div>
            </div>
        </section>
    )
}

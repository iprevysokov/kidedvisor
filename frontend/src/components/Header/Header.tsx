'use client';
import React from "react";
import "./Header.scss";
import logo from "../../images/Kidedvisor.svg";
import burgerImage from '../../images/burger.svg';
import Image from "next/image";
import { useAppDispatch, useAppSelector } from "@/src/utils/redux/store";
import { switchBurgerMenu } from "@/src/utils/redux/slices/appSlice";
import close_afford from '../../images/close_aff.svg';
import back_afford from '../../images/back_aff.svg';

export default function Header() {
  const { burgerMenuOpened } = useAppSelector(state => state.appSlice)
  const dispatch = useAppDispatch();
  function handleBurgerButtonClick() {
    dispatch(switchBurgerMenu());
  }

  return (
    <header className="header">

      {!burgerMenuOpened && (
        <button onClick={handleBurgerButtonClick}>
          <Image
            src={burgerImage.src}
            width={32}
            height={32}
            alt="Бургерное меню"
          />
        </button>
      )}
      {/* <div className='afford'>
            <button>
                <Image className='back_afford' alt='назад' src={back_afford.src} width={14} height={14}/>
            </button>
            <button>
                <Image className='close_afford' alt='закрыть' src={close_afford.src} width={14} height={14}/>
            </button>
      </div> */}
      <div>
        <Image
          className="header__logo"
          src={logo.src}
          height={61}
          width={266}
          alt="логотип"
        />
      </div>
    </header>
  );
}

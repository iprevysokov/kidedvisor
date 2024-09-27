"use client";

import { useAppSelector } from "../utils/redux/store";
import BurgerMenu from "./BurgerMenu/BurgerMenu";
import Popup from "./Popup/Popup";

export default function InitialDataLoader() {
  const { burgerMenuOpened, popup: { isOpened: isPopupOened } } = useAppSelector(state => state.appSlice)
  return (
    <>
      {burgerMenuOpened && (<BurgerMenu />)}
      {isPopupOened && (<Popup />)}
    </>
  );
}

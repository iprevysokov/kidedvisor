"use client";

import { useAppSelector } from "../utils/redux/store";
import BurgerMenu from "./BurgerMenu/BurgerMenu";

export default function InitialDataLoader() {
  const { burgerMenuOpened } = useAppSelector(state => state.appSlice)
  return (
    <>
      {burgerMenuOpened && (<BurgerMenu />)}
    </>
  );
}

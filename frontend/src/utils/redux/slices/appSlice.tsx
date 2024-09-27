import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { ReactNode } from "react";
import { IPopup } from "../../types";

interface IAppState {
  burgerMenuOpened: boolean;
  popup: IPopup;
}

const initialState: IAppState = {
  burgerMenuOpened: false,
  popup: {
    isOpened: false,
    children: (<></>)
  }
};

export const slice = createSlice({
  name: "app",
  initialState,
  reducers: {
    switchBurgerMenu(state) {
      state.burgerMenuOpened = !state.burgerMenuOpened;
    },
    openPopup(state, action: PayloadAction<ReactNode>) {
      state.popup = {
        isOpened: true,
        children: action.payload

      }
    },
    closePopup(state) {
      state.popup = initialState.popup;
    }
  },
});
export const {
  switchBurgerMenu, openPopup, closePopup
} = slice.actions;

export default slice.reducer;

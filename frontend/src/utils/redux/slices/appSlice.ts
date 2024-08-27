import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface IAppState {
  burgerMenuOpened: boolean;
}

const initialState: IAppState = {
  burgerMenuOpened: false,
};

export const slice = createSlice({
  name: "app",
  initialState,
  reducers: {
    switchBurgerMenu(state) {
      state.burgerMenuOpened = !state.burgerMenuOpened;
    }
  },
});
export const {
  switchBurgerMenu
} = slice.actions;

export default slice.reducer;

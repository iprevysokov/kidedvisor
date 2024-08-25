import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface IAppState {

}

const initialState: IAppState = {

};

export const slice = createSlice({
  name: "app",
  initialState,
  reducers: {

  },
});
export const {
} = slice.actions;

export default slice.reducer;

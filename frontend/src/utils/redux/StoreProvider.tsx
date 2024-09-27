"use client";
import { Provider } from "react-redux";
import store from "./rootReducer";
import React from "react";

export default function ReduxProvider({
  children,
}: {
  children: React.ReactNode;
}) {
  return <Provider store={store}>{children}</Provider>;
}

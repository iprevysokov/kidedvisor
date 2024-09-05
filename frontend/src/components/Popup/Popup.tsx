import React, { MouseEvent, useEffect, useState } from 'react'
import './Popup.scss';
import { useAppDispatch, useAppSelector } from '@/src/utils/redux/store';
import { closePopup } from '@/src/utils/redux/slices/appSlice';
import useOpenHandler from '@/src/hooks/useOpenHandler';

export default function Popup() {
    const { currentState, close } = useOpenHandler();

    const { popup: { children } } = useAppSelector(state => state.appSlice);
    const dispatch = useAppDispatch();
    function handleClosePopup(e: MouseEvent<HTMLDivElement>) {
        if (e.target == e.currentTarget) {
            close()
            setTimeout(() => dispatch(closePopup()), 300)
        }
    }

    return (
        <div onClick={handleClosePopup} className={`popup popup_${currentState}`}>{children}</div>
    )
}

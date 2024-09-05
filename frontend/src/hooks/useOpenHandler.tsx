import { useEffect, useState } from "react";

export default function useOpenHandler() {
    const [currentState, setCurrentState] = useState<'opened' | 'closed' | ''>('');

    function close() {
        setCurrentState('closed')
    }

    useEffect(() => {
        setCurrentState('opened')
    }, [])
    return { currentState, close }
}
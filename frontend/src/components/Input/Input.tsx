import React, { forwardRef, InputHTMLAttributes } from 'react';
import './Input.scss';

interface Props extends InputHTMLAttributes<HTMLInputElement> {
    placeholder?: string;
    type?: 'text' | 'password' | 'tel' | 'email' | 'file' | 'number';
    additionalClass?: string;
    label?: string;
}

const Input = forwardRef<HTMLInputElement, Props>(
    ({ placeholder, type = 'text', additionalClass, label, ...props }, ref) => {
        return (
            <>
                {label && (
                    <div className="label">
                        <span>{label}</span>
                    </div>
                )}
                <input
                    className={`input ${additionalClass}`}
                    type={type}
                    placeholder={placeholder}
                    ref={ref}
                    {...props}  // Передаём все остальные пропсы
                />
            </>
        );
    }
);

Input.displayName = 'Input';

export default Input;

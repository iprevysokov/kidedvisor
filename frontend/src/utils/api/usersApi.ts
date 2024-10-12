import { apiUrl } from "../constants";
import { _fetch } from "../utils";

export interface IUserRegisterDto {
    email: string;
    phone_number: string;
    first_name?: string;
    middle_name?: string;
    last_name?: string;
}

export function apiRegister(dto: IUserRegisterDto) {
    return _fetch({
        url: `users/register_parent/`,
        method: 'POST',
        body: {
            ...dto
        }
    })
}
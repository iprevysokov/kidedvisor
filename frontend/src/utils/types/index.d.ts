export interface ISectionCard {
    image: string;
    name: string;
    rating: number;
}

export interface IUser {
    email: string;
    phone_number: string;
    first_name: string;
    middle_name: string;
    last_name: string;
}

export interface IFetch {
    url: string;
    method?: "GET" | "POST" | "PUT" | "DELETE" | "PATCH";
    headers?: HeadersInit;
    body?: BodyInit | Record<string, unknown>;
  }
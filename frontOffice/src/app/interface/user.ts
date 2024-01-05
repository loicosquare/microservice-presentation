import {Rating} from "./rating";

export interface User {
  userId: string;
  name: string;
  email: string;
  about: string;
  ratings: Rating[];
}

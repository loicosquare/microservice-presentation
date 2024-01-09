import {Category} from "./category";
import {Game} from "./game";
import {Enterprise} from "./enterprise";
import {User} from "./user";
import {Rating} from "./rating";

export interface CustomHttpResponse {
  timeStamp: Date;
  statusCode: number;
  status: string;
  message: string;
  reason: string;
  //data?: Category[] | Game[] | Enterprise[] | User[] | Category | Game | Enterprise | User | Rating[] | string | null;
  data?: Game[];
}

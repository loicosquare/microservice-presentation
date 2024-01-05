import { Rating } from './rating';
export interface Game {
  gameId: string;
  title: string;
  description: string;
  releaseDate: Date; //Si ca ne marche pas on utiliser un string.
  platform: string;
  developer: string;
  publisher: string;
  price: number;
  gameLength: number;
  imageUrl: string;
  enterpriseId: string;
  categoryId: string;
  ratings: Rating[];
}

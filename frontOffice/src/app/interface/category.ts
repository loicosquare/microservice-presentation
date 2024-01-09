import { Game } from './game';
export interface Category {
  categoryId: string;
  categoryName: string;
  categoryDescription: string;
  imageUrl: string;
  games: Game[];
}

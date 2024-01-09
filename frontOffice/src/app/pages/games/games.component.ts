import {Component, OnInit} from '@angular/core';
import {HelperService} from "../../services/helper.service";
import {NotifierService} from "angular-notifier";
import {EnterpriseService} from "../../services/enterprise.service";
import {CustomHttpResponse} from "../../interface/CustomHttpResponse";
import {NotificationType} from "../../enum/notification-type.enum";
import {HttpErrorResponse} from "@angular/common/http";
import {GameService} from "../../services/game.service";
import {Game} from "../../interface/game";

@Component({
  selector: 'app-games',
  templateUrl: './games.component.html',
  styleUrls: ['./games.component.css']
})
export class GamesComponent implements OnInit{
  public games! : any;
  public gam! : any;
  public refreshing! : boolean;

  // gamess = [
  //   {
  //     "gameId": "65942c0ab567a93be7c5c4f0",
  //     "title": "Adventure Game 2",
  //     "description": "Description for Adventure Game 2",
  //     "releaseDate": "2024-01-02",
  //     "platform": "PC",
  //     "developer": "Techs Developers",
  //     "publisher": "Techs Publishers",
  //     "price": 39.99,
  //     "gameLength": 40,
  //     "imageUrl": "https://robohash.org/Adventure Game 2",
  //     "enterpriseId": "299223f1-5a76-4e19-ab12-3367e25d580d",
  //     "categoryId": "d66e63ea-f288-4ab1-9a57-0faae191bd98",
  //     "ratings": [
  //       {
  //         "ratingId": "6594340f1f2ef55a956adfdd",
  //         "userId": "00e3d87b-2bdb-4a47-9660-98214093f004",
  //         "gameId": "65942c0ab567a93be7c5c4f0",
  //         "rating": 5,
  //         "feedback": "The best of the best",
  //         "enterpriseId": "299223f1-5a76-4e19-ab12-3367e25d580d"
  //       }
  //     ]
  //   },
  //   {
  //     "gameId": "65942c0ab567a93be7c5c4f0",
  //     "title": "Adventure Game 2",
  //     "description": "Description for Adventure Game 2",
  //     "releaseDate": "2024-01-02",
  //     "platform": "PC",
  //     "developer": "Techs Developers",
  //     "publisher": "Techs Publishers",
  //     "price": 39.99,
  //     "gameLength": 40,
  //     "imageUrl": "https://robohash.org/Adventure Game 2",
  //     "enterpriseId": "299223f1-5a76-4e19-ab12-3367e25d580d",
  //     "categoryId": "d66e63ea-f288-4ab1-9a57-0faae191bd98",
  //     "ratings": [
  //       {
  //         "ratingId": "6594340f1f2ef55a956adfdd",
  //         "userId": "00e3d87b-2bdb-4a47-9660-98214093f004",
  //         "gameId": "65942c0ab567a93be7c5c4f0",
  //         "rating": 5,
  //         "feedback": "The best of the best",
  //         "enterpriseId": "299223f1-5a76-4e19-ab12-3367e25d580d"
  //       }
  //     ]
  //   },    {
  //     "gameId": "65942c0ab567a93be7c5c4f0",
  //     "title": "Adventure Game 2",
  //     "description": "Description for Adventure Game 2",
  //     "releaseDate": "2024-01-02",
  //     "platform": "PC",
  //     "developer": "Techs Developers",
  //     "publisher": "Techs Publishers",
  //     "price": 39.99,
  //     "gameLength": 40,
  //     "imageUrl": "https://robohash.org/Adventure Game 2",
  //     "enterpriseId": "299223f1-5a76-4e19-ab12-3367e25d580d",
  //     "categoryId": "d66e63ea-f288-4ab1-9a57-0faae191bd98",
  //     "ratings": [
  //       {
  //         "ratingId": "6594340f1f2ef55a956adfdd",
  //         "userId": "00e3d87b-2bdb-4a47-9660-98214093f004",
  //         "gameId": "65942c0ab567a93be7c5c4f0",
  //         "rating": 5,
  //         "feedback": "The best of the best",
  //         "enterpriseId": "299223f1-5a76-4e19-ab12-3367e25d580d"
  //       }
  //     ]
  //   }
  //   // Add more game objects here if needed
  // ];

  constructor(private helperService: HelperService,
              private gameService: GameService) {
  }

  ngOnInit(): void {
    this.getAllGames(true);
  }

  getAllGames(showNotification: boolean): void {
    this.refreshing = true;
    this.gameService.games$.subscribe(
      (data: CustomHttpResponse) => { // TODO : Remplacer any par CustomHttpResponse
        this.games = data;
        this.games = this.games.games;
        console.log("Jeux : ", this.games.games);
        this.refreshing = false;
        if (showNotification) {
          this.helperService.sendNotification(NotificationType.SUCCESS, `${data.message} successfully.`);
        }
      },
      (error : HttpErrorResponse) => {
        this.refreshing = false;
      }
    );
  }
}

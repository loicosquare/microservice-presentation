import {Component, OnInit} from '@angular/core';
import {HelperService} from "../../services/helper.service";
import {EnterpriseService} from "../../services/enterprise.service";
import {CustomHttpResponse} from "../../interface/CustomHttpResponse";
import {HttpErrorResponse} from "@angular/common/http";
import {NotifierService} from "angular-notifier";
import {NotificationType} from "../../enum/notification-type.enum";

@Component({
  selector: 'app-enterprises',
  templateUrl: './enterprises.component.html',
  styleUrls: ['./enterprises.component.css']
})
export class EnterprisesComponent implements OnInit {
  public enterprises! : any;
  public refreshing! : boolean;
  showModal: boolean = false;

  constructor(private helperService: HelperService,
              private enterprisesService: EnterpriseService) {
  }

  ngOnInit(): void {
    this.getAllEnterprises(true);
  }
  getAllEnterprises(showNotification: boolean): void {
    this.refreshing = true;
    this.enterprisesService.enterprises$.subscribe(
      (data: CustomHttpResponse) => {
        this.enterprises = data;

        // Ajout d'une image aléatoire pour chaque entreprise
        this.enterprises.forEach((entreprise : any) => {
          entreprise.image = `https://picsum.photos/seed/${entreprise.id}/300/200`; // Exemple d'URL d'image aléatoire
        });

        this.refreshing = false;
        if (showNotification) {
          this.helperService.sendNotification(NotificationType.INFO, `enterprise(s) loaded successfully.`);
        }
      },
      (error : HttpErrorResponse) => {
        this.refreshing = false;
      }
    );
  }

  // Méthodes pour gérer les clics sur les icônes
  showDetails(entreprise: any) {
    // Logique pour afficher les détails de l'entreprise
    console.log("Détails de l'entreprise : ", entreprise);
  }

  showRatings(entreprise: any) {
    this.showModal = true;
    // Logique pour afficher la liste des ratings de l'entreprise
    console.log("Ratings de l'entreprise : ", entreprise.ratings);
  }

  showGames(entreprise: any) {
    // Logique pour afficher la liste des jeux de l'entreprise
    console.log("Jeux de l'entreprise : ", entreprise.games);
  }

  hideModal() : void {
    this.showModal = false;
  }

}

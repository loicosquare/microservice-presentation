import {Component, OnInit} from '@angular/core';
import {HelperService} from "../../services/helper.service";
import {EnterpriseService} from "../../services/enterprise.service";
import {CustomHttpResponse} from "../../interface/CustomHttpResponse";
import {HttpErrorResponse} from "@angular/common/http";
import {NotificationType} from "../../enum/notification-type.enum";
import {Router} from "@angular/router";

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
              private enterprisesService: EnterpriseService,
              private router: Router) {
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
        this.helperService.sendNotification(NotificationType.ERROR, error.error.message);
      }
    );
  }

  // Méthodes pour gérer les clics sur les icônes
  showDetails(entreprise: any) {
    this.router.navigate(['/enterprise-details', entreprise.id]).then(() => {});
  }

  showRatings(entreprise: any) {
    this.showModal = true;
    console.log("Ratings de l'entreprise : ", entreprise.ratings);
  }

  showGames(entreprise: any) {

  }

  hideModal() : void {
    this.showModal = false;
  }

}

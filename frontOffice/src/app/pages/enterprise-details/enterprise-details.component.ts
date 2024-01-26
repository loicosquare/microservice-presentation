import {Component, OnInit} from '@angular/core';
import {EnterpriseService} from "../../services/enterprise.service";
import {HelperService} from "../../services/helper.service";
import {ActivatedRoute} from "@angular/router";
import {HttpErrorResponse} from "@angular/common/http";
import {NotificationType} from "../../enum/notification-type.enum";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-enterprise-details',
  templateUrl: './enterprise-details.component.html',
  styleUrls: ['./enterprise-details.component.css']
})
export class EnterpriseDetailsComponent implements OnInit {

  public enterprise! : any;
  public refreshing! : boolean;
  public enterpriseId! : number;
  public enterpriseForm = new FormGroup({});

  constructor(private helperService: HelperService,
              private enterprisesService: EnterpriseService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.enterpriseId = this.route.snapshot.params['id'];
    this.getEnterpriseById(this.enterpriseId, true);
  }

  getEnterpriseById(id: number, showNotification: boolean): void {
    this.refreshing = true;
    this.enterprisesService.enterprise$(id).subscribe(
      (data: any) => {
        this.enterprise = data;
        this.refreshing = false;
        if(this.enterprise.image == null) {
          this.enterprise.image = `https://picsum.photos/seed/${this.enterprise.id}/300/200`;
        }
        if (showNotification) {
          this.helperService.sendNotification(NotificationType.INFO, `enterprise loaded successfully.`);
        }
      },
      (error : HttpErrorResponse) => {
        this.refreshing = false;
        this.helperService.sendNotification(NotificationType.ERROR, error.error.message);
      }
    );
  }

  deleteEnterprise(entreprise: any) : void {

  }

  createEnterprise() : void {

  }
}

import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {CustomHttpResponse} from "../../interface/CustomHttpResponse";
import {HttpErrorResponse} from "@angular/common/http";
import {Category} from "../../interface/category";
import {NotifierService} from "angular-notifier";
import {Router} from "@angular/router";
import {NotificationService} from "../../services/notification.service";
import {NotificationType} from "../../enum/notification-type.enum";
import {HelperService} from "../../services/helper.service";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit{

  public categories! : any;
  public refreshing! : boolean;

  constructor(private categoryService: CategoryService,
              private router: Router,
              private notificationService: NotificationService,
              private helperService: HelperService,
              private notifierService: NotifierService) {
  }

  ngOnInit() {
    this.getCategories(true);
  }

  private getCategories(showNotification: boolean): void {
    this.refreshing = true;
    this.categoryService.categories$.subscribe(
      (response: CustomHttpResponse) => {
        this.categories = response;
        this.refreshing = false;
        if (showNotification) {
          this.helperService.sendNotification(NotificationType.SUCCESS, `category(ies) loaded successfully.`);
        }
      },
      (errorResponse: HttpErrorResponse) => {
        this.helperService.sendNotification(NotificationType.ERROR, errorResponse.error.message);
        this.refreshing = false;
      },
      () => {
        console.log('Completed');
      }
    );
  }

}

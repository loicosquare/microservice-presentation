import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {CustomHttpResponse} from "../../interface/CustomHttpResponse";
import {HttpErrorResponse} from "@angular/common/http";
import {Category} from "../../interface/category";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit{

  public categories! : any;

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.getCategories();
  }

  private getCategories(): void {
    this.categoryService.categories$.subscribe(
      (response: CustomHttpResponse) => {
        this.categories = response;
        console.log(this.categories);
      },
      (error: HttpErrorResponse) => {
        console.log(error);
      },
      () => {
        console.log('Completed');
      }
    );
  }

}

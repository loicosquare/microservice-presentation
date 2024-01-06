import {Component, OnInit} from '@angular/core';
import {CategoryService} from "../../services/category.service";
import {CustomHttpResponse} from "../../interface/CustomHttpResponse";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit{

  constructor(private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.categoryService.categories$.subscribe(
      next => (response : CustomHttpResponse) => {
        console.log(response);
      },
      error => (error : HttpErrorResponse) => {
        console.log(error);
      },
       () => {
        console.log('Completed');
      }
    );
  }
}

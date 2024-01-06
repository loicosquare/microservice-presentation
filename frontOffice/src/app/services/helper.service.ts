import { Injectable } from '@angular/core';
import {HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class HelperService {

  public backEndUrl = 'http://localhost:8084'; //Corresponds to the port number of the back-end server (API GATEWAY).
  //private const CATEGORY_SERVICE : string = 'CATEGORIE-SERVICE';
  constructor() { }

  getBackEndUrl(): string {
    return this.backEndUrl;
  }

  public handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    let errorMessage: string;
    if (error.error instanceof ErrorEvent) {
      errorMessage = `A client error occurred - ${error.error.message}`;
    } else {
      if (error.error.reason) {
        errorMessage = `${error.error.reason} - Error code ${error.status}`;
      } else {
        errorMessage = `An error occurred - Error code ${error.status}`;
      }
    }
    return throwError(errorMessage);
  }
}

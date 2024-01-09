import { Injectable } from '@angular/core';
import {HelperService} from "./helper.service";
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, tap} from "rxjs";
import {CustomHttpResponse} from "../interface/CustomHttpResponse";
import {Category} from "../interface/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private server : string = this.helperService.getBackEndUrl();
  constructor(private helperService: HelperService, private http: HttpClient) { }

  categories$ = <Observable<CustomHttpResponse>>this.http.get<CustomHttpResponse>
  (`${this.server}/categories/all`)
    .pipe(
      tap(console.log),
      catchError(this.helperService.handleError)
    );

  save$ = (note: Category) => <Observable<CustomHttpResponse>>
    this.http.post<CustomHttpResponse>
    (`${this.server}/note/add`, note)
      .pipe(
        tap(console.log),
        catchError(this.helperService.handleError)
      );

  update$ = (note: Category) => <Observable<CustomHttpResponse>>
    this.http.put<CustomHttpResponse>
    (`${this.server}/note/update`, note)
      .pipe(
        tap(console.log),
        catchError(this.helperService.handleError)
      );

  delete$ = (noteId: number) => <Observable<CustomHttpResponse>>
    this.http.delete<CustomHttpResponse>
    (`${this.server}/note/delete/${noteId}`)
      .pipe(
        tap(console.log),
        catchError(this.helperService.handleError)
      );
}

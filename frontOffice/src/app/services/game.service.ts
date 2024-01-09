import { Injectable } from '@angular/core';
import {HelperService} from "./helper.service";
import {HttpClient} from "@angular/common/http";
import {catchError, Observable, tap} from "rxjs";
import {CustomHttpResponse} from "../interface/CustomHttpResponse";

@Injectable({
  providedIn: 'root'
})
export class GameService {
  private server : string = this.helperService.getBackEndUrl();
  constructor(private helperService: HelperService, private http: HttpClient) { }

  games$ = <Observable<CustomHttpResponse>>this.http.get<CustomHttpResponse>
  (`${this.server}/games/all`)
    .pipe(
      tap(console.log),
      catchError(this.helperService.handleError)
    );
}

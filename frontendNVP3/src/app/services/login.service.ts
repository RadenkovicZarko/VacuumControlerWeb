import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpParams} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {LoginResponse} from "../model";
import {MatDialog} from "@angular/material/dialog";
import {ErrorDialogComponent} from "../error-dialog/error-dialog.component";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiUrl = 'http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users/login/';

  constructor(private http: HttpClient,private dialog: MatDialog) {}

  login(email: string, password: string): Observable<LoginResponse> {
    const params = new HttpParams()
      .set('email', email)
      .set('password', password)

    return this.http.get<LoginResponse>(this.apiUrl, { params }).pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 404) {
          this.openErrorDialog("Wrong email or password")
          return throwError(error);
        } else {
          return throwError('An error occurred:' + error.message);
        }
      })
    );
  }

  openErrorDialog(message: string): void {
    const dialogRef = this.dialog.open(ErrorDialogComponent, {
      width: '300px',
      data: { errorMessage: message }
    });
  }
}

import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../model";

@Injectable({
  providedIn: 'root'
})
export class CreateUserService {

  private apiUrl = 'http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  createUser(user: User): Observable<User> {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<User>(`${this.apiUrl}/createUsers`, user,{ headers} );
  }

}

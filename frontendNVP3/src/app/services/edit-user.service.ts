import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EditUserService {

  private apiUrl = 'http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users'; // Replace with your backend API URL

  constructor(private http: HttpClient) {
  }

  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }

  updateUser(user: User): Observable<User> {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<User>(`${this.apiUrl}/updateUsers`, user,{ headers} );
  }
}

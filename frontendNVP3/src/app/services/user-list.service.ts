import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserListService {

  private apiUrl = 'http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  getUsers(): Observable<User[]> {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<any[]>(`${this.apiUrl}/readUsers`, { headers });
  }


  getCanCreateUsers(): Observable<Object> {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    return this.http.get(`http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users/canCreate`, {headers});
  }

}

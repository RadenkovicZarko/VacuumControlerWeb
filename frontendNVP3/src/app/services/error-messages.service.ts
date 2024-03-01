import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {ErrorMessage, SearchRequest, VacuumCleaner} from "../model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ErrorMessagesService {

  private apiUrl = 'http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/errorMessage'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  findAllErrorMessagesForUser(): Observable<ErrorMessage[]> {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<ErrorMessage[]>(`${this.apiUrl}/findAll`,{ headers });
  }

}

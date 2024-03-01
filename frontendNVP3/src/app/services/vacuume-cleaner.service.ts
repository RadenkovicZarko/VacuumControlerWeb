import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {SearchRequest, User, VacuumCleaner} from "../model";

@Injectable({
  providedIn: 'root'
})
export class VacuumeCleanerService {

  private apiUrl = 'http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/vacuumCleaner'; // Replace with your backend API URL

  constructor(private http: HttpClient) {}

  searchVacuumCleaners(params: SearchRequest): Observable<VacuumCleaner[]> {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<VacuumCleaner[]>(`${this.apiUrl}/search`, params,{ headers });
  }

  addVacuumCleaners(name: string): Observable<VacuumCleaner> {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.post<VacuumCleaner>(`${this.apiUrl}/addVacuumCleaner`, {name},{ headers });
  }

  startVacuumCleaner(id:number):Observable<Response>{
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    console.log(token);
    return this.http.post<Response>(`${this.apiUrl}/start/${id}`,null,{ headers });
  }

  stopVacuumCleaner(id:number):Observable<Response>{
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    console.log(token);
    return this.http.post<Response>(`${this.apiUrl}/stop/${id}`,null,{ headers });
  }

  dischargeVacuumCleaner(id:number):Observable<Response>{
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    console.log(token);
    return this.http.post<Response>(`${this.apiUrl}/discharge/${id}`,null,{ headers });
  }

  deleteVacuumCleaner(id:number):Observable<Response>{
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    console.log(token);
    return this.http.delete<Response>(`${this.apiUrl}/${id}`,{ headers });
  }

  scheduleVacuumCleaner(requestData:any):Observable<Response>{

    return this.http.post<Response>(`${this.apiUrl}/schedule`,requestData );
  }

  getForUser(): Observable<VacuumCleaner[]> {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<VacuumCleaner[]>(`${this.apiUrl}/findForUser`, { headers });
  }
}

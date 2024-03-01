import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class CreateUserGuard implements CanActivate{

  constructor(private http: HttpClient, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    return new Observable<boolean>((observer) => {
      const token = localStorage.getItem('JWT');
      if (token) {
        const headers = new HttpHeaders({
          'Authorization': `Bearer ${token}`
        });

        this.http.get<any>(`http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users/canCreate`, { headers }).subscribe(
          (response) => {
            observer.next(true); // User can create based on response
            observer.complete();
          },
          (error) => {
            observer.next(false); // User cannot create based on error
            observer.complete();
          }
        );
      } else {
        observer.next(false); // No JWT found, user cannot create
        observer.complete();
      }
    });
  }
}

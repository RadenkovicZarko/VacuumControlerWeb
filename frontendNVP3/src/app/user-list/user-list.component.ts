import { Component } from '@angular/core';
import {UserListService} from "../services/user-list.service";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {User} from "../model";

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {
  users: any[] = [];
  permission : boolean = false;
  canCreatePermission : boolean =false;
  canUpdatePermission : boolean = false;
  canDeletePermission : boolean = false;
  constructor(private userService: UserListService, private http: HttpClient) {}


  ngOnInit(){
    this.userService.getUsers().subscribe(
      (data) => {
        this.users = data;
        this.permission = true;
      },
      (error) => {
        this.permission = false;
      }
    );


    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    this.http.get<any>(`http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users/canCreate`, {headers}).subscribe(
      (response)=>{
        this.canCreatePermission = true;
      },
      (error)=> {
        console.error('An error occurred:', error);
      }
    )
    this.http.get<any>(`http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users/canUpdate`, {headers}).subscribe(
      (response)=>{
        this.canUpdatePermission = true;
      },
      (error)=> {
        console.error('An error occurred:', error);
      }
    )
    this.http.get<any>(`http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users/canDelete`, {headers}).subscribe(
      (response)=>{
        this.canDeletePermission = true;
      },
      (error)=> {
        console.error('An error occurred:', error);
      }
    )

  }

  performAction(user : User) {
    const token = localStorage.getItem('JWT');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });
    this.http.delete<any>(`http://localhost:8080/backendNVP3-1.0-SNAPSHOT/backend/users/${user.id}`, {headers});
    this.users = this.users.filter(u => u.id !==user.id);
  }

}

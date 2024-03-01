import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {LoginService} from "../services/login.service";
import {MatDialog} from "@angular/material/dialog";
import {ErrorDialogComponent} from "../error-dialog/error-dialog.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';


  constructor(private loginService: LoginService,private dialog: MatDialog,  private router: Router) {}

  onSubmit() {
    this.loginService.login(this.email, this.password).subscribe((response) => {
      if(!response)
      {
        localStorage.removeItem('JWT');
      }
      else
      {
        localStorage.setItem('JWT', response.jwt);
        console.log(response);
        this.router.navigate(['/userList']);
      }
    });
  }

  openErrorDialog(message: string): void {
    const dialogRef = this.dialog.open(ErrorDialogComponent, {
      width: '300px',
      data: { errorMessage: message ,
              heading: 'Error'}
    });
  }
}

import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { UserListComponent } from './user-list/user-list.component';
import { ErrorDialogComponent } from './error-dialog/error-dialog.component';
import {MatDialogModule} from "@angular/material/dialog";
import { CreateUserComponent } from './create-user/create-user.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { SearchVacuumCleanerComponent } from './search-vacuum-cleaner/search-vacuum-cleaner.component';
import { AddVacuumCleanerComponent } from './add-vacuum-cleaner/add-vacuum-cleaner.component';
import { ErrorMessagesComponent } from './error-messages/error-messages.component';
import { VacuumCleanerTableComponent } from './vacuum-cleaner-table/vacuum-cleaner-table.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserListComponent,
    ErrorDialogComponent,
    CreateUserComponent,
    UserListComponent,
    EditUserComponent,
    SearchVacuumCleanerComponent,
    AddVacuumCleanerComponent,
    ErrorMessagesComponent,
    VacuumCleanerTableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

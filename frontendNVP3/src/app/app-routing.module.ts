import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./login/login.component";
import {UserListComponent} from "./user-list/user-list.component";
import {CreateUserComponent} from "./create-user/create-user.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import {AuthGuard} from "./auth.guard";
import {CreateUserGuard} from "./create-user.guard";
import {EditUserGuard} from "./edit-user.guard";
import {SearchVacuumCleanerComponent} from "./search-vacuum-cleaner/search-vacuum-cleaner.component";
import {AddVacuumCleanerComponent} from "./add-vacuum-cleaner/add-vacuum-cleaner.component";
import {ErrorMessagesComponent} from "./error-messages/error-messages.component";
import {SearchVacuumeCleanerGuard} from "./search-vacuume-cleaner.guard";
import {AddVacuumCleanerGuard} from "./add-vacuum-cleaner.guard";
import {VacuumCleanerTableComponent} from "./vacuum-cleaner-table/vacuum-cleaner-table.component";

const routes: Routes = [
  {
    path: "",
    component: LoginComponent,
  },
  {
    path: "userList",
    component: UserListComponent,
    canActivate: [AuthGuard],
  },
  {
    path: "createUser",
    component: CreateUserComponent,
    canActivate: [CreateUserGuard],
  },
  {
    path: 'editUser/:id', component: EditUserComponent,
    canActivate: [EditUserGuard]
  },
  {
    path:'searchVacuumeCleaner',component:SearchVacuumCleanerComponent,
    canActivate:[SearchVacuumeCleanerGuard]

  },
  {
    path:'addVacuumCleaner',component:AddVacuumCleanerComponent,
    canActivate:[AddVacuumCleanerGuard]
  },
  {
    path:'errorMessage',component:ErrorMessagesComponent,
  },
  {
    path:'vacuumCleanerTable',component:VacuumCleanerTableComponent,
    canActivate:[SearchVacuumeCleanerGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

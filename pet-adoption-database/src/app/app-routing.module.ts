import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PetsComponent } from './pets/pets.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './auth/component/login/login.component';
import { AuthguardService } from './auth/service/authguard.service';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { RequestPageComponent } from './components/requests/request-page/request-page.component';
import { ReceiptPageComponent } from './components/receipts/receipt-page/receipt-page.component';

//Testing
import { ViewPetsComponent } from './test/components/view-pets/view-pets.component';
import { LogoutComponent } from './auth/component/logout/logout.component';
import { SignUpComponent } from './auth/component/sign-up/sign-up.component';
import { AddPetComponent } from './test/components/add-pet/add-pet.component';
import { AccountInfoComponent } from './components/employee-dashboard/components/account-info/account-info.component';

import { AdoptionComponent } from './components/adoption/adoption.component';

import { SupportComponent } from './components/employee-dashboard/components/support/support.component';
import { AddEmployeeComponent } from './components/employee/add-employee/add-employee.component';
import { ViewEmployeesComponent } from './components/employee/view-employees/view-employees.component';
import { PetSearchComponent } from './components/pet-search/pet-search.component';
import { PasswordResetComponent } from './auth/component/password-reset/password-reset.component';
import { VerifyUsernameComponent } from './auth/component/verify-username/verify-username.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'pets', component: PetSearchComponent },
  { path: 'login', component: LoginComponent },
  { path: 'adoption/:petId', component: AdoptionComponent },
  {
    path: 'requests',
    component: RequestPageComponent,
    canActivate: [AuthguardService],
  },
  {
    path: 'receipts',
    component: ReceiptPageComponent,
    canActivate: [AuthguardService],
  },
  { path: 'verify-user', component: VerifyUsernameComponent },
  { path: 'pass-reset', component: PasswordResetComponent },
  {
    path: 'requests',
    component: RequestPageComponent,
    canActivate: [AuthguardService],
  },
  {
    path: 'receipts',
    component: ReceiptPageComponent,
    canActivate: [AuthguardService],
  },

  {
    path: 'employeeDashboard',
    component: EmployeeDashboardComponent,
    canActivate: [AuthguardService],
    children: [
      {
        path: 'addAPet',
        component: AddPetComponent,
      },
      { path: 'accountInfo', component: AccountInfoComponent },
      { path: 'receipts', component: ReceiptPageComponent },
      { path: 'requests', component: RequestPageComponent },
      { path: 'support', component: SupportComponent },
      { path: 'view-employees', component: ViewEmployeesComponent },
    ],
  },

  //Testing
  { path: 'test', component: ViewPetsComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'pet-add-test', component: AddPetComponent },

  { path: 'add-employee', component: AddEmployeeComponent },
  { path: 'view-employees', component: ViewEmployeesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

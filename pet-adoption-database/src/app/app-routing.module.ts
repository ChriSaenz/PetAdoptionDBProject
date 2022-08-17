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
import { SupportComponent } from './components/employee-dashboard/components/support/support.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'pets', component: PetsComponent },
  { path: 'login', component: LoginComponent },
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
    ],
  },

  //Testing
  { path: 'test', component: ViewPetsComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'signup', component: SignUpComponent },
  { path: 'pet-add-test', component: AddPetComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

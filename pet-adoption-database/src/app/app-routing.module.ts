import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/component/login/login.component';
import { LogoutComponent } from './auth/component/logout/logout.component';
import { AuthguardService } from './auth/service/authguard.service';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { RequestPageComponent } from './components/requests/request-page/request-page.component';
import { CartComponent } from './test/components/cart/cart.component';
import { DashboardComponent } from './test/components/dashboard/dashboard.component';
import { EmployeeLoginComponent } from './test/components/employee-login/employee-login.component';
import { EmployeeMenuComponent } from './test/components/employee-menu/employee-menu.component';
import { ViewPetsComponent } from './test/components/view-pets/view-pets.component';
const routes: Routes = [
  { path: 'login', component:LoginComponent},
  { path: 'logout', component:LogoutComponent},
  {path: 'employeeDashboard', component:EmployeeDashboardComponent, canActivate:[AuthguardService]},
  {path: 'requests', component:RequestPageComponent, canActivate:[AuthguardService]},
  {path: '', component: DashboardComponent},
  {path: 'cartTest', component: CartComponent},
  {path: 'empoloyeeLoginTest', component: EmployeeLoginComponent},
  {path: 'employeeMenuTest', component: EmployeeMenuComponent},
  {path: 'viewPetsTest', component: ViewPetsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

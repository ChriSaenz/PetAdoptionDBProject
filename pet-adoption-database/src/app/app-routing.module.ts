import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/component/login/login.component';
import { LogoutComponent } from './auth/component/logout/logout.component';
import { AuthguardService } from './auth/service/authguard.service';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { RequestPageComponent } from './components/requests/request-page/request-page.component';

const routes: Routes = [
  { path: 'login', component:LoginComponent},
  { path: 'logout', component:LogoutComponent},
  {path: 'employeeDashboard', component:EmployeeDashboardComponent, canActivate:[AuthguardService]},
  {path: 'requests', component:RequestPageComponent, canActivate:[AuthguardService]}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

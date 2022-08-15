import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PetsComponent } from './pets/pets.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './auth/component/login/login.component';
import { AuthguardService } from './auth/service/authguard.service';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';

//Testing
import { ViewPetsComponent } from './test/components/view-pets/view-pets.component';
import { LogoutComponent } from './auth/component/logout/logout.component';
import { SignUpComponent } from './auth/component/sign-up/sign-up.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'pets', component: PetsComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'employeeDashboard',
    component: EmployeeDashboardComponent,
    canActivate: [AuthguardService],
  },

  //Testing
  { path: 'test', component: ViewPetsComponent },
  { path: 'logout', component: LogoutComponent },
  { path: 'signup', component: SignUpComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

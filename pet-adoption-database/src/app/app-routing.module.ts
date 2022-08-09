import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './test/components/cart/cart.component';
import { DashboardComponent } from './test/components/dashboard/dashboard.component';
import { EmployeeLoginComponent } from './test/components/employee-login/employee-login.component';
import { EmployeeMenuComponent } from './test/components/employee-menu/employee-menu.component';
import { ViewPetsComponent } from './test/components/view-pets/view-pets.component';

const routes: Routes = [
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

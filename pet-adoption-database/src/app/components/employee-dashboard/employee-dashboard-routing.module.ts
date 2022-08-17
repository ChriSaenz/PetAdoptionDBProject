import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddPetComponent } from 'app/test/components/add-pet/add-pet.component';
import { ViewEmployeesComponent } from '../employee/view-employees/view-employees.component';

const routes: Routes = [
  { path: 'pet-add-test', component: AddPetComponent },
  { path: 'viewAllEmployees', component: ViewEmployeesComponent, },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class EmployeeDashboardRoutingModule {}

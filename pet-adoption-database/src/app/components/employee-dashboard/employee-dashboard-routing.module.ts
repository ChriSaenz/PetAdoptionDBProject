import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddPetComponent } from 'app/test/components/add-pet/add-pet.component';

const routes: Routes = [{ path: 'pet-add-test', component: AddPetComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class EmployeeDashboardRoutingModule {}

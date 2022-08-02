import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './test/components/dashboard/dashboard.component';
import { EmployeeLoginComponent } from './test/components/employee-login/employee-login.component';
import { CartComponent } from './test/components/cart/cart.component';
import { ViewPetsComponent } from './test/components/view-pets/view-pets.component';
import { EmployeeMenuComponent } from './test/components/employee-menu/employee-menu.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    EmployeeLoginComponent,
    CartComponent,
    ViewPetsComponent,
    EmployeeMenuComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

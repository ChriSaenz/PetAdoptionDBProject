import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RequestListComponent } from './components/requests/request-list/request-list.component';
import { RequestFormComponent } from './components/requests/request-form/request-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { RequestPageComponent } from './components/requests/request-page/request-page.component';
import { LoginComponent } from './auth/component/login/login.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { LogoutComponent } from './auth/component/logout/logout.component';
import { DashboardComponent } from './test/components/dashboard/dashboard.component';
import { EmployeeLoginComponent } from './test/components/employee-login/employee-login.component';
import { CartComponent } from './test/components/cart/cart.component';
import { ViewPetsComponent } from './test/components/view-pets/view-pets.component';
import { EmployeeMenuComponent } from './test/components/employee-menu/employee-menu.component';
import { RequestSearchComponent } from './components/requests/request-search/request-search.component';

@NgModule({
  declarations: [
    AppComponent,
    RequestListComponent,
    RequestFormComponent,
    RequestPageComponent,
    LoginComponent,
    EmployeeDashboardComponent,
    LogoutComponent,
    DashboardComponent,
    EmployeeLoginComponent,
    CartComponent,
    ViewPetsComponent,
    EmployeeMenuComponent,
    RequestSearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

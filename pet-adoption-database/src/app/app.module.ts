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

@NgModule({
  declarations: [
    AppComponent,
    RequestListComponent,
    RequestFormComponent,
    RequestPageComponent,
    LoginComponent,
    EmployeeDashboardComponent,
    LogoutComponent
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

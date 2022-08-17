import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { PetsComponent } from './pets/pets.component';

import { LoginComponent } from './auth/component/login/login.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { LogoutComponent } from './auth/component/logout/logout.component';
import { ProfileComponent } from './auth/component/profile/profile.component';
import { SignUpComponent } from './auth/component/sign-up/sign-up.component';
import { EmployeeDashboardComponent } from './components/employee-dashboard/employee-dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { RequestFormComponent } from './components/requests/request-form/request-form.component';
import { RequestListComponent } from './components/requests/request-list/request-list.component';
import { RequestPageComponent } from './components/requests/request-page/request-page.component';
import { RequestSearchComponent } from './components/requests/request-search/request-search.component';
import { ReceiptPageComponent } from './components/receipts/receipt-page/receipt-page.component';
import { ReceiptListComponent } from './components/receipts/receipt-list/receipt-list.component';
import { ReceiptSearchComponent } from './components/receipts/receipt-search/receipt-search.component';
import { MatInputModule } from '@angular/material/input';
import { FlexLayoutModule } from '@angular/flex-layout';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSelectModule } from '@angular/material/select';
import { MatRippleModule } from '@angular/material/core';
import { ViewPetsComponent } from './test/components/view-pets/view-pets.component';
import { AddPetComponent } from './test/components/add-pet/add-pet.component';
import { AccountInfoComponent } from './components/employee-dashboard/components/account-info/account-info.component';
import { HomeComponent } from './components/home/home.component';
import { SupportComponent } from './components/employee-dashboard/components/support/support.component';
import { AddEmployeeComponent } from './components/employee/add-employee/add-employee.component';
import { ViewEmployeesComponent } from './components/employee/view-employees/view-employees.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { PetSearchComponent } from './components/pet-search/pet-search.component';
import { PasswordResetComponent } from './auth/component/password-reset/password-reset.component';
import { VerifyUsernameComponent } from './auth/component/verify-username/verify-username.component';

@NgModule({
  declarations: [
    AppComponent,
    PetsComponent,
    HomeComponent,
    LoginComponent,
    LogoutComponent,
    ProfileComponent,
    SignUpComponent,
    EmployeeDashboardComponent,
    RequestFormComponent,
    RequestListComponent,
    RequestPageComponent,
    RequestSearchComponent,
    ReceiptPageComponent,
    ReceiptListComponent,
    ReceiptSearchComponent,
    ViewPetsComponent,
    AddPetComponent,
    AccountInfoComponent,
    SupportComponent,
    AddEmployeeComponent,
    ViewEmployeesComponent,
    PetSearchComponent
    PasswordResetComponent,
    VerifyUsernameComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    HttpClientModule,
    MatInputModule,
    FlexLayoutModule,
    MatSidenavModule,
    MatSelectModule,
    MatRippleModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}

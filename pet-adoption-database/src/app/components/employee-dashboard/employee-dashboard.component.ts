import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthService } from 'app/auth/service/auth.service';

@Component({
  selector: 'app-employee-dashboard',
  templateUrl: './employee-dashboard.component.html',
  styleUrls: ['./employee-dashboard.component.css'],
})
export class EmployeeDashboardComponent implements OnInit {
  currentRole: string;

  constructor(
    private authService: AuthService,
    private _formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    this.authService
      .getUserByUsername(localStorage.getItem('credentials'))
      .subscribe({
        next: (data) => {
          this.currentRole = data.role;
          console.log(this.currentRole);
        },
      });
  }

  currentTab() {}

  test() {
    this.authService
      .getUserByUsername(localStorage.getItem('credentials'))
      .subscribe({
        next: (data) => {
          this.currentRole = data.role;
          console.log(this.currentRole);
        },
      });
  }
}

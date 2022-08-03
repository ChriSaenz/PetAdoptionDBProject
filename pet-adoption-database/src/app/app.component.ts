import { Component, OnInit } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Employee } from './model/employee.model';
import { AuthService } from './auth/service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {

  constructor(private authService: AuthService) { }
  employees: Employee[];
  username: string;

  ngOnInit(): void {
    // this.emplyoees = this.employeeService.fetchEmployees();
    this.authService.username$.subscribe(data => {
      if (data != "") this.username = data;
      console.log("Username: " + this.username);
    })
  }
  title = 'pet-adoption-database';

}

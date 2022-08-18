import { Component, OnDestroy, OnInit } from '@angular/core';
import { Employee } from './model/employee.model';
import { AuthService } from './auth/service/auth.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit, OnDestroy {

  constructor(private authService: AuthService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }
  employees: Employee[];
  username: string;
  subscriptions:Subscription[]=[];

  ngOnInit(): void {
    // this.emplyoees = this.employeeService.fetchEmployees();
    this.subscriptions.push(
    this.authService.username$.subscribe(data => {
      if (data != "") this.username = data;
      // console.log("Username: " + this.username);
    }))
  }
  title = 'pet-adoption-database';
  applySorts(): void
  {
    console.log("Test")
  }
}

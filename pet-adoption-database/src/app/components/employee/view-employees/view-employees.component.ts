import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthService } from 'app/auth/service/auth.service';
import { Employee } from 'app/model/employee.model';
import { UserDto } from 'app/model/user.model';
import { EmployeeService } from 'app/service/employee.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-view-employees',
  templateUrl: './view-employees.component.html',
  styleUrls: ['./view-employees.component.css']
})
export class ViewEmployeesComponent implements OnInit, OnDestroy {
  subscriptions: Subscription[] = [];
  employees: Employee[] = [];
  errorMsg: string = '';

  add: boolean
  newEmployee: Employee
  newUser: UserDto
  addEmployeeForm: FormGroup
  addEmployeeAccountForm: FormGroup
  msg: string

  constructor(private employeeService: EmployeeService, private authService: AuthService) {}

  ngOnInit(): void {
    this.subscriptions.push(
      this.employeeService.getAllEmployees().subscribe({
        next: (data) => {
          this.employees = data;
        },
        error: (e) => {
          console.log(
            '[ViewEmployees] Error when subscribing to employee$ from EmployeeService'
          );
        },
      })
    );

    this.addEmployeeForm = new FormGroup({
      //For Employee
      name: new FormControl('', [Validators.required]),
      phone: new FormControl('', [Validators.required]),
      salary: new FormControl('', [Validators.required]),
      title: new FormControl('', [Validators.required]),
    })

    this.addEmployeeAccountForm = new FormGroup({
      //For User
      nickname: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      securityQuestion: new FormControl('', [Validators.required]),
      securityAnswer: new FormControl('', [Validators.required]),
    });

    this.add = false
    this.msg = ""
  }

  onDeleteEmployee(eid: number)
  {
    this.employeeService.deleteByEmployeeId(eid).subscribe({
      next: (data) => {
        this.employees = this.employees.filter(e => e.id != eid)
      },
      error: (e) => { console.log("[onDeleteEmployee] Error when trying to delete employee (Id: " + eid + ")") }
    })
  }

  addEmployeeStart()
  {
    this.add = true;
  }

  onFormSubmit() {
    this.newEmployee = this.addEmployeeForm.value;


    //Add Employee
    // console.log(this.addEmployeeForm.value.name)
    // this.newEmployee.name = this.addEmployeeForm.value.name
    // this.newEmployee.phone = this.addEmployeeForm.value.phone
    // this.newEmployee.title = this.addEmployeeForm.value.title
    // this.newEmployee.salary = this.addEmployeeForm.value.salary
    
    this.employeeService.postEmployee(this.newEmployee).subscribe({
      next: (data) => {
        this.newEmployee = data;
        this.msg = 'Employee added to db!';

        let empArr = this.employeeService.employee$.getValue();

        empArr.push(this.newEmployee);

        this.employeeService.employee$.next(empArr);

        //stat
      },
      error: (e) => {
        console.log('[AddEmployee-onFormSubmit] Error when trying to add new employee');
      },
    });

    //Add Employee User Account
    this.newUser = {
      nickname: this.addEmployeeAccountForm.value.nickname,
      role: "EMPLOYEE",
      securityQuestion: this.addEmployeeAccountForm.value.securityQuestion,
      securityAnswer: this.addEmployeeAccountForm.value.securityAnswer,
      encodedCredentials: btoa(
        this.addEmployeeAccountForm.value.username + '---' + this.addEmployeeAccountForm.value.password
      ),
    };

    this.authService.signUp(this.newUser).subscribe({
      next: (data) => {console.log('[AddEmployee-onFormSubmit] Employee Account added to DB')},
      error: (e) => {console.log("[AddEmployee-onFormSubmit] Error when trying to add new employee account")},
    });

    this.add = false;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((s) => s.unsubscribe());
  }
}

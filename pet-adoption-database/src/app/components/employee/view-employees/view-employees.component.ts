import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Employee } from 'app/model/employee.model';
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
  addEmployeeForm: FormGroup
  msg: string

  constructor(private employeeService: EmployeeService) {}

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
      name: new FormControl('', [Validators.required]),
      phone: new FormControl('', [Validators.required]),
      salary: new FormControl('', [Validators.required]),
      title: new FormControl('', [Validators.required]),
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
    console.log("It lives!")
    this.add = true;
  }

  onFormSubmit() {
    this.newEmployee = this.addEmployeeForm.value;

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

    this.add = false;
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((s) => s.unsubscribe());
  }
}

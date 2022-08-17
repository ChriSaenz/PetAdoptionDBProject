import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from 'app/model/employee.model';
import { environment } from 'environments/environment';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  getAllEmployeesApi: string
  getEmployeeByIdApi: string
  postEmployeeApi: string
  deleteByEmployeeIdApi: string
  employee$ = new BehaviorSubject<Employee[]>([])

  constructor(private http: HttpClient) {
    this.getAllEmployeesApi = environment.serverURL + "/employee"
    this.getEmployeeByIdApi = environment.serverURL + "/employee/"
    this.postEmployeeApi = environment.serverURL + "/employee"
    this.deleteByEmployeeIdApi = environment.serverURL + "/employee/"
  }

  getAllEmployees(): Observable<Employee[]> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'application/json',
        'Authorization': 'basic ' + localStorage.getItem("credentials")
      })
    }

    return this.http.get<Employee[]>(this.getAllEmployeesApi, httpOptions);
  }

  getEmployeeById(id: number): Observable<Employee> {
    return this.http.get<Employee>(this.getEmployeeByIdApi + id)
  }

  postEmployee(emp: Employee): Observable<Employee> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'application/json',
        'Authorization': 'basic ' + localStorage.getItem("credentials")
      })
    }

    return this.http.post<Employee>(this.postEmployeeApi, emp, httpOptions);
  }

  deleteByEmployeeId(id: number): Observable<Employee> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'application/json',
        'Authorization': 'basic ' + localStorage.getItem("credentials")
      })
    }

    return this.http.delete<Employee>(this.deleteByEmployeeIdApi + id, httpOptions)
  }
}

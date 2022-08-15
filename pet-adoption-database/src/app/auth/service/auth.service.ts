import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from 'app/model/employee.model';
import { UserDto } from 'app/model/user.model';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  username$ = new BehaviorSubject<string>('');
  message$ = new BehaviorSubject<string>('');
  loginApi: string;
  signUpApi: string;

  constructor(private http: HttpClient) {
    this.username$.next('');
    this.loginApi = 'http://localhost:8824/login';
    this.signUpApi = 'http://localhost:8824/user';
  }

  isLoggedIn(): boolean {
    let username = localStorage.getItem('username');
    if (username == null || username == undefined) {
      return false;
    }
    this.username$.next(localStorage.getItem('username'));
    return true;
  }

  signUp(userDto: UserDto): Observable<any> {
    return this.http.post(this.signUpApi, userDto);
  }

  login(username: string, password: string): Observable<Employee> {
    let encoded = btoa(username + ':' + password);
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + encoded,
      }),
    };
    return this.http.get<Employee>(this.loginApi, httpOptions);
  }
}

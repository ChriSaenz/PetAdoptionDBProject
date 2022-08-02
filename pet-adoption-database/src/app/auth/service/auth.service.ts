import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Employee } from 'src/app/model/employee.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  username$ = new BehaviorSubject<string>("")
  message$ = new BehaviorSubject<string>("");
  loginApi : string;

  constructor(private http: HttpClient) {
    this.username$.next("");
    this.loginApi = 'http://localhost:8824/login'
  }

  isLoggedIn(): boolean {
    this.username$.next(localStorage.getItem('username'));
    if (this.username$ == null) return false;
    return true;
  }

  login(username: string, password: string) {
    let encoded = btoa(username + ":" + password);
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':'application/json',
        'Authorization':'basic' + encoded
      })
    }
    return this.http.get<Employee>(this.loginApi, httpOptions);

  }
}

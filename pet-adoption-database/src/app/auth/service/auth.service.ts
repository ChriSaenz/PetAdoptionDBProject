import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Employee } from 'app/model/employee.model';
import { UserDto, UserEditDto, UserSecurityDto } from 'app/model/user.model';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  username$ = new BehaviorSubject<string>('');
  message$ = new BehaviorSubject<string>('');
  user$ = new BehaviorSubject<string>('');
  loginApi: string;
  signUpApi: string;
  userApi: string;
  editProfileApi: string;
  passResetApi: string;
  securityInfoApi: string;
  securityAnswerValidationApi: string;

  constructor(private http: HttpClient) {
    this.username$.next('');
    this.loginApi = 'http://localhost:8824/login';
    this.signUpApi = 'http://localhost:8824/user';
    this.userApi = 'http://localhost:8824/user/username';
    this.passResetApi = 'http://localhost:8824/user/reset-password/';
    this.securityInfoApi = 'http://localhost:8824/user/security/info/';
    this.securityAnswerValidationApi = 'http://localhost:8824/verify/';
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

  getUserByUsername(credentials: string): Observable<UserDto> {
    let httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'application/json',
        Authorization: 'basic ' + credentials,
      }),
    };
    return this.http.get<UserDto>(this.userApi, httpOptions);
  }

  resetPassword(username: string, password: string): Observable<any> {
    let encodedText = btoa(username + '---' + password);
    return this.http.put(this.passResetApi + encodedText, {});
  }

  validateSecurityAnswer(
    username: string,
    answer: string
  ): Observable<boolean> {
    let encodedText = btoa(username + '---' + answer);

    return this.http.get<boolean>(
      this.securityAnswerValidationApi + encodedText
    );
  }

  getUserSecurityDetailsByUsername(
    username: string
  ): Observable<UserSecurityDto> {
    return this.http.get<UserSecurityDto>(this.securityInfoApi + username);
  }
}

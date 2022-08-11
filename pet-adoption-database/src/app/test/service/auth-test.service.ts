import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/userTest.model';

@Injectable({
  providedIn: 'root'
})
export class AuthTestService {

  username: string

  //Global service variables
  username$ = new BehaviorSubject<string>('')
  msg$ = new BehaviorSubject<string>('')

  //API paths
  loginApi: string

  constructor(private http: HttpClient) {
    this.loginApi = environment.serverURL + '/login'
  }

  isLoggedIn(): boolean
  {
    this.username = localStorage.getItem('username')
    if(this.username == null)
      return false
    return true    
  }

  login(username: string, password: string): Observable<User>
  {
    let encodedCredentials = btoa(username + ":" + password)
    let httpOptions =
        {
            headers: new HttpHeaders({
                'Content-type': 'application/json',
                'Authorization': 'basic ' + encodedCredentials
            })
        }

        // console.log(encodedCredentials)
        return this.http.get<User>(this.loginApi, httpOptions)
  }
}

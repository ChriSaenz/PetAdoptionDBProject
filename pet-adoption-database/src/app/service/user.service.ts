import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from 'app/model/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  getUserByUsernameApi:string = "http://localhost:8824/user/username"

  constructor(private http:HttpClient) {}

  getUserByUsername(username:string): Observable<User> {
    return this.http.get<User>(this.getUserByUsernameApi + username);
  }
}

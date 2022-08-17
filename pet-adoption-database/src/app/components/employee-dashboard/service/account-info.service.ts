import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDto, UserEditDto } from 'app/model/user.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AccountInfoService {
  userApi: string;

  constructor(private http: HttpClient) {
    this.userApi = 'http://localhost:8824/user/username';
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
}

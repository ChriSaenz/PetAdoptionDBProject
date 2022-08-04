import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  username: string;
  message$ = new BehaviorSubject<string>('');
  constructor() {
    this.username = '';
  }

  isLoggedIn(): boolean {
    this.username = localStorage.getItem('username');
    if (this.username == null) return false;
    return true;
  }

  login(username: string, password: string) {}
}

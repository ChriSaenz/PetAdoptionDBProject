import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-logout',
  templateUrl: './logout.component.html',
  styleUrls: ['./logout.component.css'],
})
export class LogoutComponent implements OnInit {
  message: string;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    localStorage.clear();
    this.authService.message$.next('Log out successful.. Refreshing Page');

    this.authService.message$.subscribe((data) => {
      this.message = data;
    });
  }
}

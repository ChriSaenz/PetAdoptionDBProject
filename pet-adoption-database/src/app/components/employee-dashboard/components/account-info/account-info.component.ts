import { Component, OnInit } from '@angular/core';
import { AuthService } from 'app/auth/service/auth.service';
import { UserDto } from 'app/model/user.model';

@Component({
  selector: 'app-account-info',
  templateUrl: './account-info.component.html',
  styleUrls: ['./account-info.component.css'],
})
export class AccountInfoComponent implements OnInit {
  userDto: UserDto;
  username: string;
  credentials: string;
  securityQuestion: string;
  securityAnswer: string;
  role: string;
  nickname: string;
  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username');
    this.credentials = localStorage.getItem('credentials');

    this.authService.getUserByUsername(this.credentials).subscribe({
      next: (data) => {
        this.userDto = data;
        this.securityQuestion = data.securityQuestion;
        this.securityAnswer = data.securityAnswer;
        this.nickname = data.nickname;
      },
    });
  }

  test() {}
}

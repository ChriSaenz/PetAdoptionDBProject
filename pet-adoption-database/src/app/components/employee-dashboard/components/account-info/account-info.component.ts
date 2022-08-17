import { Component, OnInit } from '@angular/core';
import { AuthService } from 'app/auth/service/auth.service';
import { UserDto } from 'app/model/user.model';
import { AccountInfoService } from '../../service/account-info.service';

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
  constructor(private accountService: AccountInfoService) {}

  ngOnInit(): void {
    this.username = localStorage.getItem('username');
    this.credentials = localStorage.getItem('credentials');

    this.accountService.getUserByUsername(this.credentials).subscribe({
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

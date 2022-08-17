import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'app/auth/service/auth.service';
import { UserSecurityDto } from 'app/model/user.model';

@Component({
  selector: 'app-verify-username',
  templateUrl: './verify-username.component.html',
  styleUrls: ['./verify-username.component.css'],
})
export class VerifyUsernameComponent implements OnInit {
  username: string;
  error: string;
  dto: UserSecurityDto;
  status: boolean;
  answer: string;
  showSecurityBox: boolean;
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.status = true;
    this.error = '';
    this.showSecurityBox = false;
  }

  onFormSubmit() {
    this.authService.getUserSecurityDetailsByUsername(this.username).subscribe({
      next: (data) => {
        this.dto = data;
        this.status = false;
        console.log(this.dto);
        this.showSecurityBox = true;
      },
      error: (e) => {
        this.error = 'username invalid';
      },
    });
  }

  onQSubmit() {
    this.authService
      .validateSecurityAnswer(this.username, this.answer)
      .subscribe({
        next: (data) => {
          if (data == true) {
            this.authService.user$.next(this.username);
            this.router.navigateByUrl('/pass-reset');
          } else {
            this.authService.message$.next(
              'Security Info could not be verified'
            );
            this.router.navigateByUrl('/login');
          }
        },
        error: (e) => {},
      });
  }
}

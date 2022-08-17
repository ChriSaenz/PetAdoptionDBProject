import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'app/auth/service/auth.service';

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.css'],
})
export class PasswordResetComponent implements OnInit {
  password: string;
  newpassword: string;
  error: string;
  username: string;
  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.authService.user$.subscribe((data) => {
      this.username = data;
    });
  }

  onReset() {
    if (this.password === this.password) {
      this.authService.resetPassword(this.username, this.password).subscribe({
        next: (data) => {
          this.authService.message$.next('Your Password has been Reset!');
          this.router.navigateByUrl('/login');
        },
        error: (e) => {
          this.error = 'Reset Failure';
        },
      });
    }
  }
}

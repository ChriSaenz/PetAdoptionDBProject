import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'app/auth/service/auth.service';
import { UserDto } from 'app/model/user.model';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent implements OnInit {
  signUpForm: FormGroup;
  user: UserDto;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.signUpForm = new FormGroup({
      nickname: new FormControl('', [Validators.required]),
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      securityQuestion: new FormControl('', [Validators.required]),
      securityAnswer: new FormControl('', [Validators.required]),
    });
  }
  onFormSubmit() {
    this.user = {
      nickname: this.signUpForm.value.nickname,
      role: 'USER',
      securityQuestion: this.signUpForm.value.securityQuestion,
      securityAnswer: this.signUpForm.value.securityAnswer,
      encodedCredentials: btoa(
        this.signUpForm.value.username + '---' + this.signUpForm.value.password
      ),
    };

    this.authService.signUp(this.user).subscribe({
      next: (data) => {
        this.authService.message$.next(
          'Thank you for signing up! Please login to continue.'
        );
        this.router.navigateByUrl('/login');
      },
      error: (e) => {},
    });
  }
}

import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  userDto: UserDto;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.signUpForm = new FormGroup({
      nickname: new FormControl(''),
      username: new FormControl(''),
      password: new FormControl(''),
      role: new FormControl(''),
      securityQuestion: new FormControl(''),
      securityAnswer: new FormControl(''),
    });
  }
  onFormSubmit() {
    this.userDto = {
      nickname: this.signUpForm.value.nickname,
      role: this.signUpForm.value.role,
      securityQuestion: this.signUpForm.value.securityQuestion,
      securityAnswer: this.signUpForm.value.securityAnswer,
      encodedCredentials: btoa(
        this.signUpForm.value.username + '---' + this.signUpForm.value.password
      ),
    };

    this.authService.signUp(this.userDto).subscribe({
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

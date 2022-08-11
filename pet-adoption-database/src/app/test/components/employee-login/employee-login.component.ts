import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { User } from '../../model/userTest.model';
import { AuthTestService } from '../../service/auth-test.service';

@Component({
  selector: 'app-employee-login',
  templateUrl: './employee-login.component.html',
  styleUrls: ['./employee-login.component.css']
})
export class EmployeeLoginComponent implements OnInit, OnDestroy {

  subscriptions: Subscription[] = []
  loginForm: FormGroup
  message: string
  username: string
  password: string
  user: User

  constructor(private authService: AuthTestService, private router: Router) { }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl('')
    })
    this.subscriptions.push(
      this.authService.msg$.subscribe(data => {
        // this.message$ = data
        this.message = data
      })
    )
  }

  onFormSubmit() {
    this.username = this.loginForm.value.username
    this.password = this.loginForm.value.password

    this.subscriptions.push(
      this.authService.login(this.username, this.password).subscribe({
        next: (data) => {
          this.user = data
          localStorage.setItem('username', this.user.username)
          localStorage.setItem('credentials', btoa(this.username + ':' + this.password))
          this.authService.username$.next(this.user.username)
          this.router.navigateByUrl('/dashboard')
        },
        error: (e) => {
          this.authService.msg$.next("Invalid Credentials")
        }
      })
    )
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe())
  }
}

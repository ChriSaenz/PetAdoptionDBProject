import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {
  requestForm: FormGroup;
  constructor() { }

  ngOnInit(): void {
    this.requestForm = new FormGroup({
      c_id: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)]),
      p_id: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)])

    })
  }

  onFormSubmit(): void {
    console.log(this.requestForm.value());
  }

}

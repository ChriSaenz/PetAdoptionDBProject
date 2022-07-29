import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { PetRequest } from 'src/app/model/petrequest.model';
import { RequestService } from 'src/app/service/request.service';

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit {
  requestForm: FormGroup;
  message:string;
  constructor(private requestService:RequestService) { }

  ngOnInit(): void {
    this.message="";
    this.requestForm = new FormGroup({
      c_id: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)]),
      p_id: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)]),
      e_id: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)])
    })
  }

  onFormSubmit(): void {
    let request = new PetRequest();
    this.requestService.postRequest(request, this.requestForm.value.e_id, this.requestForm.value.p_id, this.requestForm.value.c_id).subscribe({
      next: (data) => {
        this.message="Request added successfully"
      },
      error: (e) => {
        this.message="Could not perform operation"
      }
    })
  }

}

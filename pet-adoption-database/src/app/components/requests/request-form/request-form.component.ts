import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { PetRequest } from '../model/petrequest.model';
import { RequestService } from '../service/request.service';

@Component({
  selector: 'app-request-form',
  templateUrl: './request-form.component.html',
  styleUrls: ['./request-form.component.css']
})
export class RequestFormComponent implements OnInit, OnDestroy {
  requestForm: FormGroup;
  message:string;
  subscriptions:Subscription[]=[];
  request:PetRequest;
  constructor(private requestService:RequestService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }

  ngOnInit(): void {
    this.message="";
    this.requestForm = new FormGroup({
      c_id: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)]),
      p_id: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)]),
      e_id: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]+$/)])
    })
  }

  onFormSubmit(){
    this.request = this.requestForm.value;
    this.subscriptions.push(
     this.requestService.postRequest(this.request, this.requestForm.value.c_id, this.requestForm.value.p_id, 
      this.requestForm.value.e_id).subscribe( {
        next: (data)=> {
          this.request = data;
          this.message='request added in the system';
          //Read the value from the Subject
          let requestArry = this.requestService.request$.getValue();
          //update the value: add request to request[]
          requestArry.push(this.request);
          //update the subject value
          this.requestService.request$.next(requestArry);

          //update the value of $stat
          //this.requestService.stat$.next(true);
        },
        error: (e)=>{
          this.message='Operation Failed';
        }
     }))
  }
}

import { Component, OnInit } from '@angular/core';
import { PetRequest } from '../model/petrequest.model';
import { RequestService } from '../service/request.service';

@Component({
  selector: 'app-request-page',
  templateUrl: './request-page.component.html',
  styleUrls: ['./request-page.component.css']
})
export class RequestPageComponent implements OnInit {

  requests : PetRequest[];

  constructor(private requestService:RequestService) { }

  ngOnInit(): void {
    this.requestService.fetchRequests()
    .subscribe({
      next: (data)=>{
          this.requests = data;
          this.requestService.request$.next(this.requests);
      },
      error: (e)=>{
        //redirect to error page
      }
    });
  }

}

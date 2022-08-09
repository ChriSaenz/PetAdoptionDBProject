import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { PetRequest } from '../model/petrequest.model';
import { RequestService } from '../service/request.service';

@Component({
  selector: 'app-request-page',
  templateUrl: './request-page.component.html',
  styleUrls: ['./request-page.component.css']
})
export class RequestPageComponent implements OnInit, OnDestroy {

  requests : PetRequest[];
  subscriptions:Subscription[]=[];

  constructor(private requestService:RequestService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }

  ngOnInit(): void {
    this.subscriptions.push(
    this.requestService.fetchRequests()
    .subscribe({
      next: (data)=>{
          this.requests = data;
          this.requestService.request$.next(this.requests);
      },
      error: (e)=>{
        //redirect to error page
      }
    }))
  }

}

import { Component, OnInit } from '@angular/core';
import { PetRequest } from '../model/petrequest.model';
import { RequestService } from '../service/request.service';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {
  errorMsg: string;
  requests: PetRequest[];
  request: PetRequest;
  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
    this.requestService.request$.subscribe(data => {
      this.requests = data;
    });
  }

  /* ngOnInit(): void {
     this.errorMsg = "";
     this.requestService.fetchRequests().subscribe({
       next: (data) => {
         this.requests = data;
       },
       error: (e) => {
         this.errorMsg = "Requests could not be fetched"
       }
     });
 
   }*/

  delete(id: number) {
    this.requestService.delete(id).subscribe({
      next: (data) => {
        let requestArry = this.requestService.request$.getValue();
        for (var request of requestArry) {
          if (request.id == id) this.request = request;
        }
        const index = requestArry.indexOf(this.request, 0);
        if (index > -1) {
          requestArry.splice(index, 1);
        }
        this.requestService.request$.next(requestArry);
        this.errorMsg = "Deletion successful"
      },
      error: (e) => {
        this.errorMsg = "Deletion unsuccessful"
      }
    })
    console.log(this.errorMsg);
  }

  approve(id: number) {
    this.requestService.approve(id).subscribe({
      next: (data) => {
        let requestArry = this.requestService.request$.getValue();
        for (var request of requestArry) {
          if (request.id == id) request.status="Approved";
        }
        this.requestService.request$.next(requestArry);
        this.errorMsg = "Approval successful"
      },
      error: (e) => {
        this.errorMsg = "Approval unsuccessful"
      }
    })
    console.log(this.errorMsg);
  }

  reject(id: number) {
    this.requestService.reject(id).subscribe({
      next: (data) => {
        let requestArry = this.requestService.request$.getValue();
        for (var request of requestArry) {
          if (request.id == id) request.status="Rejected";
        }
        this.requestService.request$.next(requestArry);
        this.errorMsg = "Rejection successful"
      },
      error: (e) => {
        this.errorMsg = "Rejection unsuccessful"
      }
    })
    console.log(this.errorMsg);
  }

}

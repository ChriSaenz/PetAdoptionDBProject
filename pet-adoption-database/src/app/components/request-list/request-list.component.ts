import { Component, OnInit } from '@angular/core';
import { PetRequest } from 'src/app/model/petrequest.model';
import { RequestService } from 'src/app/service/request.service';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {
  errorMsg: string;
  requests: PetRequest[];
  constructor(private requestService: RequestService) { }

  ngOnInit(): void {
    this.errorMsg = "";
    this.requestService.fetchRequests().subscribe({
      next: (data) => {
        this.requests = data;
      },
      error: (e) => {
        this.errorMsg = "Requests could not be fetched"
      }
    });

  }

  delete(id:number) {
    console.log("clicked");
    this.requestService.delete(id).subscribe({
      next: (data) => {
        this.errorMsg = "Deletion successful"
      },
      error: (e) => {
        this.errorMsg = "Deletion unsuccessful"
      }
    })
  }

}

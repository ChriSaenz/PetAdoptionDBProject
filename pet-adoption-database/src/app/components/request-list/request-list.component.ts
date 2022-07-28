import { Component, OnInit } from '@angular/core';
import { PetRequest } from 'src/app/model/petrequest.model';
import { requestData } from '../../data';


@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit {

  requests:PetRequest[];
  constructor() { }

  ngOnInit(): void {
    this.requests = requestData;
  }

}

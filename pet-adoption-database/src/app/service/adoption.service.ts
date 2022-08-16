import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PetRequest } from '../components/requests/model/petrequest.model';

@Injectable({
  providedIn: 'root'
})
export class AdoptionService {
  postApi:"ERROR";
  constructor(private http:HttpClient) { }

  makeNewRequest(pr:PetRequest) {
    this.http.post<any>(this.postApi, pr);
  }
}

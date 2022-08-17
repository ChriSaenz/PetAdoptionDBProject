import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PetRequest } from '../components/requests/model/petrequest.model';

@Injectable({
  providedIn: 'root'
})
export class AdoptionService {
  postApi:"http://localhost:8824/request";
  constructor(private http:HttpClient) { }

  makeNewRequest(pr:PetRequest, cid:number, pid:number) {
    this.http.post<any>(this.postApi + "/" + cid + "/" + pid + "/0", pr);
  }
}

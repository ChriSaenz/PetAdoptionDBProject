import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PetRequest } from '../model/petrequest.model';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  url: string;

  constructor(private http: HttpClient) {
    this.url = "http://localhost:8824/request";
  }

  delete(id: number) : Observable<any> {
    return this.http.delete(this.url + "/" + id);
  }

  fetchRequests(): Observable<PetRequest[]> {
    return this.http.get<PetRequest[]>(this.url);
  }

  postRequest(request: PetRequest, eid: number, cid: number, pid: number): Observable<PetRequest> {
    return this.http.post(this.url + "/" + cid + "/" + pid + "/" + eid, request);
  }
}

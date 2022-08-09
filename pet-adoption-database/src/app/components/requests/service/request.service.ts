import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PetRequest } from '../model/petrequest.model';

@Injectable({
  providedIn: 'root'
})
export class RequestService {
  filterByBeforeDate(p: any) {
    return this.http.get<PetRequest[]>(this.url + "/before?date=" + p);
  }
  filterByAfterDate(p: string) {
    return this.http.get<PetRequest[]>(this.url + "/after?date=" + p);
  }
  filterByCustomer(p: number) {
    return this.http.get<PetRequest[]>(this.url + "/customer/" + p);
  }
  filterByApproved() {
    return this.http.get<PetRequest[]>(this.url + "/status/approved");
  }

  filterByPending() {
    return this.http.get<PetRequest[]>(this.url + "/status/pending");
  }

  filterByRejected() {
    return this.http.get<PetRequest[]>(this.url + "/status/rejected");
  }
  filterByEmployee(p: any) {
    return this.http.get<PetRequest[]>(this.url + "/employee/" + p);
  }
  filterById(id: string) {
    return this.http.get<PetRequest>(this.url + "/" + id);
  }
  filterByPet(p: string) {
    return this.http.get<PetRequest[]>(this.url + "/pet/" + p);
  }

  url: string;

  request$ =  new BehaviorSubject<PetRequest[]>([]);

  constructor(private http: HttpClient) {
    this.url = "http://localhost:8824/request";
  }

  delete(id: number): Observable<any> {
    return this.http.delete(this.url + "/" + id);
  }

  fetchRequests(): Observable<PetRequest[]> {
    return this.http.get<PetRequest[]>(this.url);
  }

  postRequest(request: PetRequest, eid: number, cid: number, pid: number): Observable<PetRequest> {
    return this.http.post<PetRequest>(this.url + "/" + cid + "/" + pid + "/" + eid, request);
  }
  approve(id: number): Observable<any> {
    return this.http.put(this.url + "/approve/" + id, null);
  }
  reject(id: number): Observable<any> {
    return this.http.put(this.url + "/reject/" + id, null);
  }
}

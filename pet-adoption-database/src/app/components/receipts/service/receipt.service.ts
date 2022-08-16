import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Receipt } from '../model/receipt.model';

@Injectable({
  providedIn: 'root'
})
export class ReceiptService {
  filterByCost(p: any) {
    return this.http.get<Receipt[]>(this.url + "/equalTo/" + p);
  }
  filterByOnDate(p: any) {
    return this.http.get<Receipt[]>(this.url + "/at?date=" + p);
  }
  filterByBetweenCost(p: any, v: any) {
    return this.http.get<Receipt[]>(this.url + "/range/" + p + "/" + v);
  }
  filterByUnderCost(p: any) {
    return this.http.get<Receipt[]>(this.url + "/lessThan/" + p);
  }
  filterByOverCost(p: any) {
    return this.http.get<Receipt[]>(this.url + "/greaterThan/" + p);
  }
  filterByRequest(p: any) {
    return this.http.get<Receipt[]>(this.url + "/request/" + p);
  }
  filterByEmployee(p: any) {
    return this.http.get<Receipt[]>(this.url + "/employee/" + p);
  }

  filterByBetweenDates(p: any, v: any) {
    return this.http.get<Receipt[]>(this.url + "/between?from=" + p + "&to=" + v);
  }
  filterByBeforeDate(p: any) {
    return this.http.get<Receipt[]>(this.url + "/before?date=" + p);
  }
  filterByAfterDate(p: string) {
    return this.http.get<Receipt[]>(this.url + "/after?date=" + p);
  }
  filterByCustomer(p: number) {
    return this.http.get<Receipt[]>(this.url + "/customer/" + p);
  }

  filterById(id: string) {
    return this.http.get<Receipt>(this.url + "/" + id);
  }
  filterByPet(p: string) {
    return this.http.get<Receipt[]>(this.url + "/pet/" + p);
  }

  url: string;

  receipt$ =  new BehaviorSubject<Receipt[]>([]);

  constructor(private http: HttpClient) {
    this.url = "http://localhost:8824/receipt";
  }

  delete(id: number): Observable<any> {
    return this.http.delete(this.url + "/" + id);
  }

  fetchreceipts(): Observable<Receipt[]> {
    return this.http.get<Receipt[]>(this.url);
  }
}

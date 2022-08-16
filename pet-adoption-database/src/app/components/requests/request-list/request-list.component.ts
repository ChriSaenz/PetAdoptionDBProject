import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Receipt } from '../../receipts/model/receipt.model';
import { ReceiptService } from '../../receipts/service/receipt.service';
import { PetRequest } from '../model/petrequest.model';
import { RequestService } from '../service/request.service';

@Component({
  selector: 'app-request-list',
  templateUrl: './request-list.component.html',
  styleUrls: ['./request-list.component.css']
})
export class RequestListComponent implements OnInit, OnDestroy {
  errorMsg: string;
  requests: PetRequest[];
  request: PetRequest;
  subscriptions: Subscription[] = [];
  constructor(private requestService: RequestService, private receiptService: ReceiptService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub => sub.unsubscribe);
  }

  ngOnInit(): void {
    this.subscriptions.push(
      this.requestService.request$.subscribe(data => {
        this.requests = data;
      }))
  }

  delete(id: number) {
    if (!this.receiptDeleted(id)) {
      this.errorMsg = "Corresponding receipt must be deleted first"
      return;
    }
    this.subscriptions.push(
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
      }))
    console.log(this.errorMsg);
  }

  approve(id: number) {
    this.subscriptions.push(
      this.requestService.approve(id).subscribe({
        next: (data) => {
          let requestArry = this.requestService.request$.getValue();
          for (var request of requestArry) {
            if (request.id == id) request.status = "Approved";
          }
          this.requestService.request$.next(requestArry);
          this.errorMsg = "Approval successful"
        },
        error: (e) => {
          this.errorMsg = "Approval unsuccessful"
        }
      }))
    console.log(this.errorMsg);
  }

  reject(id: number) {
    this.subscriptions.push(
      this.requestService.reject(id).subscribe({
        next: (data) => {
          let requestArry = this.requestService.request$.getValue();
          for (var request of requestArry) {
            if (request.id == id) request.status = "Rejected";
          }
          this.requestService.request$.next(requestArry);
          this.errorMsg = "Rejection successful"
        },
        error: (e) => {
          this.errorMsg = "Rejection unsuccessful"
        }
      }))
    console.log(this.errorMsg);
  }

  receiptDeleted(id: number): boolean {
    let receipts = this.receiptService.receipt$.value;
    for (var receipt of receipts) {
      if (receipt.r_id == id) return false;
    }
    return true;
  }

}

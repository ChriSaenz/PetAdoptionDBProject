import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Receipt } from '../model/receipt.model';
import { ReceiptService } from '../service/receipt.service';

@Component({
  selector: 'app-request-page',
  templateUrl: './receipt-page.component.html',
  styleUrls: ['./receipt-page.component.css']
})
export class ReceiptPageComponent implements OnInit, OnDestroy {

  receipts : Receipt[];
  subscriptions:Subscription[]=[];

  constructor(private receiptService:ReceiptService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }

  ngOnInit(): void {
    this.subscriptions.push(
    this.receiptService.fetchreceipts()
    .subscribe({
      next: (data)=>{
          this.receipts = data;
          this.receiptService.receipt$.next(this.receipts);
      },
      error: (e)=>{
        //redirect to error page
      }
    }))
  }

}

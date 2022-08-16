import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Receipt } from '../model/receipt.model';
import { ReceiptService } from '../service/receipt.service';

@Component({
  selector: 'app-receipt-list',
  templateUrl: './receipt-list.component.html',
  styleUrls: ['./receipt-list.component.css']
})
export class ReceiptListComponent implements OnInit, OnDestroy {
  errorMsg: string;
  receipts: Receipt[];
  receipt: Receipt;
  subscriptions:Subscription[]=[];
  constructor(private receiptService: ReceiptService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }

  ngOnInit(): void {
    this.subscriptions.push(
    this.receiptService.receipt$.subscribe(data => {
      this.receipts = data;
    }))
  }

  delete(id: number) {
    this.subscriptions.push(
    this.receiptService.delete(id).subscribe({
      next: (data) => {
        let receiptArry = this.receiptService.receipt$.getValue();
        for (var receipt of receiptArry) {
          if (receipt.id == id) this.receipt = receipt;
        }
        const index = receiptArry.indexOf(this.receipt, 0);
        if (index > -1) {
          receiptArry.splice(index, 1);
        }
        this.receiptService.receipt$.next(receiptArry);
        this.errorMsg = "Deletion successful"
      },
      error: (e) => {
        this.errorMsg = "Deletion unsuccessful"
      }
    }))
    console.log(this.errorMsg);
  }

}

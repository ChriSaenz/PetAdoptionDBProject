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
  average : number;
  total: number;
  min : number;
  max :number;
  num : number;
  subscriptions:Subscription[]=[];
  constructor(private receiptService: ReceiptService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }

  updateMetrics() {
    this.total = 0;
    this.max = 0;
    this.num = this.receipts.length;
    this.min = Number.MAX_SAFE_INTEGER;
    for (var r of this.receipts) {
      this.total += r.cost;
      if (r.cost > this.max) this.max = r.cost;
      if (r.cost < this.min) this.min = r.cost;
    }
    this.average = this.total / this.num;
  }

  ngOnInit(): void {
    this.subscriptions.push(
    this.receiptService.receipt$.subscribe(data => {
      this.receipts = data;
      this.updateMetrics();
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
    this.updateMetrics();
  }

}

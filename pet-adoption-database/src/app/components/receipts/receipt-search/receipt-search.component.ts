import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { Receipt } from '../model/receipt.model';
import { ReceiptService } from '../service/receipt.service';

@Component({
  selector: 'app-receipt-search',
  templateUrl: './receipt-search.component.html',
  styleUrls: ['./receipt-search.component.css']
})
export class ReceiptSearchComponent implements OnInit, OnDestroy {
  filterByPetForm: FormGroup;
  searchOnDateForm: FormGroup;
  filterByIdForm: FormGroup;
  filterByEmployeeForm: FormGroup;
  filterByCustomerForm : FormGroup;
  searchAfterDateForm : FormGroup;
  searchCostForm : FormGroup;
  searchBeforeDateForm : FormGroup;
  filterByRequestForm : FormGroup;
  searchOverCostForm : FormGroup;
  searchUnderCostForm : FormGroup;
  receipts: Receipt[];
  subscriptions:Subscription[]=[];

  constructor(private receiptService: ReceiptService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }

  ngOnInit(): void {
    this.searchCostForm = new FormGroup({
      cost: new FormControl('', [Validators.required]),
    })
    this.searchUnderCostForm = new FormGroup({
      cost: new FormControl('', [Validators.required]),
    })
    this.searchOverCostForm = new FormGroup({
      cost: new FormControl('', [Validators.required]),
    })
    this.filterByPetForm = new FormGroup({
      p_id: new FormControl('', [Validators.required]),
    })
    this.filterByRequestForm = new FormGroup({
      p_id: new FormControl('', [Validators.required]),
    })
    this.filterByIdForm = new FormGroup({
      id: new FormControl('', [Validators.required]),
    })
    this.filterByEmployeeForm = new FormGroup({
      e_id: new FormControl('', [Validators.required]),
    })
    this.filterByCustomerForm = new FormGroup({
      c_id: new FormControl('', [Validators.required]),
    })
    this.searchOnDateForm = new FormGroup({
      date: new FormControl('', [Validators.required])
    })
    this.searchAfterDateForm = new FormGroup({
      date: new FormControl('', [Validators.required])
    })
    this.searchBeforeDateForm = new FormGroup({
      date: new FormControl('', [Validators.required])
    })
  }

  reset() {
    this.subscriptions.push(
    this.receiptService.fetchreceipts()
      .subscribe({
        next: (data) => {
          this.receipts = data;
          this.receiptService.receipt$.next(this.receipts);
        },
        error: (e) => {
        }
      }))
  }



  filterByPet(): void {
    let p = this.filterByPetForm.value.p_id;
    this.subscriptions.push(
    this.receiptService.filterByPet(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  filterByEmployee(): void {
    let p = this.filterByEmployeeForm.value.e_id;
    this.subscriptions.push(
    this.receiptService.filterByEmployee(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  filterByCustomer(): void {
    let p = this.filterByCustomerForm.value.e_id;
    this.subscriptions.push(
    this.receiptService.filterByCustomer(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }


  filterByRequestId(): void {
    let p = this.filterByCustomerForm.value.e_id;
    this.subscriptions.push(
    this.receiptService.filterByRequest(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  searchOverCost(): void {
    let p = this.searchOverCostForm.value.cost;
    this.subscriptions.push(
    this.receiptService.filterByOverCost(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  searchCost(): void {
    let p = this.searchCostForm.value.cost;
    this.subscriptions.push(
    this.receiptService.filterByCost(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  filterByBetweeCost (): void {
    let p = this.searchOverCostForm.value.cost;
    let v = this.searchUnderCostForm.value.cost;
    this.subscriptions.push(
    this.receiptService.filterByBetweenCost(p, v).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  searchUnderCost(): void {
    let p = this.searchUnderCostForm.value.cost;
    this.subscriptions.push(
    this.receiptService.filterByUnderCost(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }
  
  searchAfterDate(): void {
    let p = this.searchAfterDateForm.value.date;
    this.subscriptions.push(
    this.receiptService.filterByAfterDate(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  searchOnDate(): void {
    let p = this.searchOnDateForm.value.date;
    this.subscriptions.push(
    this.receiptService.filterByOnDate(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  searchBeforeDate(): void {
    let p = this.searchBeforeDateForm.value.date;
    this.subscriptions.push(
    this.receiptService.filterByBeforeDate(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  filterByBetween(): void {
    let p = this.searchAfterDateForm.value.date;
    let v = this.searchBeforeDateForm.value.date;
    this.subscriptions.push(
    this.receiptService.filterByBetweenDates(p, v).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  filterById(): void {
    let id = this.filterByIdForm.value.id;
    this.subscriptions.push(
    this.receiptService.filterById(id).subscribe({
      next: (data) => {
        var newList: Receipt[] = [data];
        this.receiptService.receipt$.next(newList);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

}

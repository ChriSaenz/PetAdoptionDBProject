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
  subscriptions:Subscription[]=[];
  message : string;

  constructor(private receiptService: ReceiptService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }

  ngOnInit(): void {
    this.message = null;
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
          this.receiptService.receipt$.next(data);
          this.message = "Reset successful"
        },
        error: (e) => {
          this.message = "Reset unsuccessful"
        }
      }))
  }



  filterByPet(): void {
    let p = this.filterByPetForm.value.p_id;
    this.subscriptions.push(
    this.receiptService.filterByPet(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for pet " + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for pet " + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }

  filterByEmployee(): void {
    let p = this.filterByEmployeeForm.value.e_id;
    this.subscriptions.push(
    this.receiptService.filterByEmployee(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for employee " + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for employee " + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }

  filterByCustomer(): void {
    let p = this.filterByCustomerForm.value.c_id;
    this.subscriptions.push(
    this.receiptService.filterByCustomer(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for customer " + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for customer " + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }


  filterByRequestId(): void {
    let p = this.filterByRequestForm.value.p_id;
    this.subscriptions.push(
    this.receiptService.filterByRequest(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for request " + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for request " + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }

  searchOverCost(): void {
    let p = this.searchOverCostForm.value.cost;
    this.subscriptions.push(
    this.receiptService.filterByOverCost(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for receipts over $" + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipts over $" + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }

  searchCost(): void {
    let p = this.searchCostForm.value.cost;
    this.subscriptions.push(
    this.receiptService.filterByCost(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for receipts = $" + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipts = $" + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
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
        this.message = "Searching for receipts between $" + p + " and $" + v + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipts between $" + p + " and $" + v + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }

  searchUnderCost(): void {
    let p = this.searchUnderCostForm.value.cost;
    this.subscriptions.push(
    this.receiptService.filterByUnderCost(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for receipts under $" + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipts under $" + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }
  
  searchAfterDate(): void {
    let p = this.searchAfterDateForm.value.date;
    this.subscriptions.push(
    this.receiptService.filterByAfterDate(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for receipts after " + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipts after " + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }

  searchOnDate(): void {
    let p = this.searchOnDateForm.value.date;
    this.subscriptions.push(
    this.receiptService.filterByOnDate(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for receipts on " + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipts on " + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }

  searchBeforeDate(): void {
    let p = this.searchBeforeDateForm.value.date;
    this.subscriptions.push(
    this.receiptService.filterByBeforeDate(p).subscribe({
      next: (data) => {
        this.receiptService.receipt$.next(data);
        this.message = "Searching for receipts before " + p + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipts before " + p + " unsuccessful"
        this.receiptService.receipt$.next([]);
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
        this.message = "Searching for receipts between " + p + " and " + v + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipts between " + p + " and " + v + " unsuccessful"
        this.receiptService.receipt$.next([]);
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
        this.message = "Searching for receipt " + id + " successful"
      },
      error: (e) => {
        this.message = "Searching for receipt " + id + " unsuccessful"
        this.receiptService.receipt$.next([]);
      }
    }))
  }

}

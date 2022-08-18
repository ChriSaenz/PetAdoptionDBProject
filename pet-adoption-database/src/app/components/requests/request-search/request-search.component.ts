import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { PetRequest } from '../model/petrequest.model';
import { RequestService } from '../service/request.service';

@Component({
  selector: 'app-request-search',
  templateUrl: './request-search.component.html',
  styleUrls: ['./request-search.component.css'],
})
export class RequestSearchComponent implements OnInit, OnDestroy {
  filterByPetForm: FormGroup;
  searchOverCostForm: FormGroup;
  searchUnderCostForm: FormGroup;
  searchOnDateForm: FormGroup;
  searchEqualCostForm: FormGroup;
  filterByIdForm: FormGroup;
  filterByEmployeeForm: FormGroup;
  filterByCustomerForm: FormGroup;
  searchAfterDateForm: FormGroup;
  searchBeforeDateForm: FormGroup;
  subscriptions: Subscription[] = [];
  message : string;

  constructor(private requestService: RequestService) {}
  ngOnDestroy(): void {
    this.subscriptions.forEach((sub) => sub.unsubscribe);
  }

  ngOnInit(): void {
    this.message = null;
    this.searchUnderCostForm = new FormGroup({
      cost: new FormControl('', [Validators.required]),
    });
    this.searchEqualCostForm = new FormGroup({
      cost: new FormControl('', [Validators.required]),
    });
    this.searchOverCostForm = new FormGroup({
      cost: new FormControl('', [Validators.required]),
    });
    this.filterByPetForm = new FormGroup({
      p_id: new FormControl('', [Validators.required]),
    });
    this.filterByIdForm = new FormGroup({
      id: new FormControl('', [Validators.required]),
    });
    this.filterByEmployeeForm = new FormGroup({
      e_id: new FormControl('', [Validators.required]),
    });
    this.filterByCustomerForm = new FormGroup({
      c_id: new FormControl('', [Validators.required]),
    });
    this.searchOnDateForm = new FormGroup({
      date: new FormControl('', [Validators.required]),
    });
    this.searchAfterDateForm = new FormGroup({
      date: new FormControl('', [Validators.required]),
    });
    this.searchBeforeDateForm = new FormGroup({
      date: new FormControl('', [Validators.required]),
    });
  }

  reset() {
    this.subscriptions.push(
      this.requestService.fetchRequests().subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Reset successful";
        },
        error: (e) => {this.message = "Reset successful";},
      })
    );
  }

  searchOverCost(): void {
    let p = this.searchOverCostForm.value.cost;
    this.subscriptions.push(
      this.requestService.filterByOverCost(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching requests over $" + p + " successful";
        },
        error: (e) => {
          this.message = "Searching requests over $" + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  searchEqualCost(): void {
    let p = this.searchEqualCostForm.value.cost;
    this.subscriptions.push(
      this.requestService.filterByEqualCost(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching requests = $" + p + " successful";
        },
        error: (e) => {
          this.message = "Searching requests = $" + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterByBetweeCost(): void {
    let p = this.searchOverCostForm.value.cost;
    let v = this.searchUnderCostForm.value.cost;
    this.subscriptions.push(
      this.requestService.filterByBetweenCost(p, v).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching requests between $" + p + " and $" + v + " successful";
        },
        error: (e) => {
          this.message = "Searching requests between $" + p + " and $" + v + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  searchUnderCost(): void {
    let p = this.searchUnderCostForm.value.cost;
    this.subscriptions.push(
      this.requestService.filterByUnderCost(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching requests under $" + p + " successful";
        },
        error: (e) => {
          this.message = "Searching requests under $" + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterByPet(): void {
    let p = this.filterByPetForm.value.p_id;
    this.subscriptions.push(
      this.requestService.filterByPet(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for pet " + p + " successful";
        },
        error: (e) => {
          this.message = "Searching for pet " + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterByEmployee(): void {
    let p = this.filterByEmployeeForm.value.e_id;
    console.log(p);
    this.subscriptions.push(
      this.requestService.filterByEmployee(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for employee " + p + " successful";
        },
        error: (e) => {
          this.message = "Searching for employee " + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterByCustomer(): void {
    let p = this.filterByCustomerForm.value.c_id;
    this.subscriptions.push(
      this.requestService.filterByCustomer(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for customer " + p + " successful";
        },
        error: (e) => {
          this.message = "Searching for customer " + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  searchOnDate(): void {
    let p = this.searchOnDateForm.value.date;
    console.log(p);
    this.subscriptions.push(
      this.requestService.filterByOnDate(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for requests on " + p + " successful";
        },
        error: (e) => {
          this.message = "Searching for requests on " + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  searchAfterDate(): void {
    let p = this.searchAfterDateForm.value.date;
    this.subscriptions.push(
      this.requestService.filterByAfterDate(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for requests after " + p + " successful";
        },
        error: (e) => {
          this.message = "Searching for requests after " + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  searchBeforeDate(): void {
    let p = this.searchBeforeDateForm.value.date;
    this.subscriptions.push(
      this.requestService.filterByBeforeDate(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for requests before " + p + " successful";
        },
        error: (e) => {
          this.message = "Searching for requests before " + p + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterByBetween(): void {
    let p = this.searchAfterDateForm.value.date;
    let v = this.searchBeforeDateForm.value.date;
    this.subscriptions.push(
      this.requestService.filterByBetweenDates(p, v).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for requests between " + p + " and " + v + " successful";
        },
        error: (e) => {
          this.message = "Searching for requests between " + p + " and " + v + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterById(): void {
    let id = this.filterByIdForm.value.id;
    this.subscriptions.push(
      this.requestService.filterById(id).subscribe({
        next: (data) => {
          var newList: PetRequest[] = [data];
          this.requestService.request$.next(newList);
          this.message = "Searching for request " + id + " successful";
        },
        error: (e) => {
          this.message = "Searching for request " + id + " unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterByApproved(): void {
    this.subscriptions.push(
      this.requestService.filterByApproved().subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for approved requests successful";
        },
        error: (e) => {
          this.message = "Searching for approved requests unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterByRejected(): void {
    this.subscriptions.push(
      this.requestService.filterByRejected().subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for rejected requests successful";
        },
        error: (e) => {
          this.message = "Searching for rejected requests unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }

  filterByPending(): void {
    this.subscriptions.push(
      this.requestService.filterByPending().subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          this.message = "Searching for pending requests successful";
        },
        error: (e) => {
          this.message = "Searching for pending requests unsuccessful";
          this.requestService.request$.next([]);
        },
      })
    );
  }
}

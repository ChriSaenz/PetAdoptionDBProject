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

  constructor(private requestService: RequestService) {}
  ngOnDestroy(): void {
    this.subscriptions.forEach((sub) => sub.unsubscribe);
  }

  ngOnInit(): void {
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
<<<<<<< HEAD
      this.requestService.fetchRequests()
        .subscribe({
          next: (data) => {
            this.requestService.request$.next(data);
          },
          error: (e) => {
          }
        }))
=======
      this.requestService.fetchRequests().subscribe({
        next: (data) => {
          this.requests = data;
          this.requestService.request$.next(this.requests);
        },
        error: (e) => {},
      })
    );
>>>>>>> chase-spring
  }

  searchOverCost(): void {
    let p = this.searchOverCostForm.value.cost;
    this.subscriptions.push(
      this.requestService.filterByOverCost(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
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
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
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
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
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
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
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
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
        },
      })
    );
  }

  filterByEmployee(): void {
    let p = this.filterByEmployeeForm.value.e_id;
    this.subscriptions.push(
      this.requestService.filterByEmployee(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
        },
      })
    );
  }

  filterByCustomer(): void {
    let p = this.filterByCustomerForm.value.e_id;
    this.subscriptions.push(
      this.requestService.filterByCustomer(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
        },
      })
    );
  }

  searchOnDate(): void {
    let p = this.searchAfterDateForm.value.date;
    this.subscriptions.push(
      this.requestService.filterByOnDate(p).subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
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
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
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
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
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
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
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
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
        },
      })
    );
  }

  filterByApproved(): void {
    this.subscriptions.push(
      this.requestService.filterByApproved().subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
        },
      })
    );
  }

  filterByRejected(): void {
    this.subscriptions.push(
      this.requestService.filterByRejected().subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
        },
      })
    );
  }

  filterByPending(): void {
    this.subscriptions.push(
      this.requestService.filterByPending().subscribe({
        next: (data) => {
          this.requestService.request$.next(data);
          console.log('filter successful');
        },
        error: (e) => {
          console.log('filter unsuccessful');
        },
      })
    );
  }
}

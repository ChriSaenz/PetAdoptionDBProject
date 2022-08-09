import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { PetRequest } from '../model/petrequest.model';
import { RequestService } from '../service/request.service';

@Component({
  selector: 'app-request-search',
  templateUrl: './request-search.component.html',
  styleUrls: ['./request-search.component.css']
})
export class RequestSearchComponent implements OnInit, OnDestroy {
  filterByPetForm: FormGroup;
  filterByIdForm: FormGroup;
  filterByEmployeeForm: FormGroup;
  filterByCustomerForm : FormGroup;
  searchAfterDateForm : FormGroup;
  searchBeforeDateForm : FormGroup;
  requests: PetRequest[];
  subscriptions:Subscription[]=[];

  constructor(private requestService: RequestService) { }
  ngOnDestroy(): void {
    this.subscriptions.forEach(sub=>sub.unsubscribe);
  }

  ngOnInit(): void {
    this.filterByPetForm = new FormGroup({
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
    this.searchAfterDateForm = new FormGroup({
      date: new FormControl('', [Validators.required])
    })
    this.searchBeforeDateForm = new FormGroup({
      date: new FormControl('', [Validators.required])
    })
  }

  reset() {
    this.subscriptions.push(
    this.requestService.fetchRequests()
      .subscribe({
        next: (data) => {
          this.requests = data;
          this.requestService.request$.next(this.requests);
        },
        error: (e) => {
        }
      }))
  }



  filterByPet(): void {
    let p = this.filterByPetForm.value.p_id;
    this.subscriptions.push(
    this.requestService.filterByPet(p).subscribe({
      next: (data) => {
        this.requestService.request$.next(data);
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
    this.requestService.filterByEmployee(p).subscribe({
      next: (data) => {
        this.requestService.request$.next(data);
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
    this.requestService.filterByCustomer(p).subscribe({
      next: (data) => {
        this.requestService.request$.next(data);
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
    this.requestService.filterByAfterDate(p).subscribe({
      next: (data) => {
        this.requestService.request$.next(data);
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
    this.requestService.filterByBeforeDate(p).subscribe({
      next: (data) => {
        this.requestService.request$.next(data);
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
    this.requestService.filterById(id).subscribe({
      next: (data) => {
        var newList: PetRequest[] = [data];
        this.requestService.request$.next(newList);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  filterByApproved(): void {
    this.subscriptions.push(
    this.requestService.filterByApproved().subscribe({
      next: (data) => {
        this.requestService.request$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  filterByRejected(): void {
    this.subscriptions.push(
    this.requestService.filterByRejected().subscribe({
      next: (data) => {
        this.requestService.request$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

  filterByPending(): void {
    this.subscriptions.push(
    this.requestService.filterByPending().subscribe({
      next: (data) => {
        this.requestService.request$.next(data);
        console.log("filter successful");
      },
      error: (e) => {
        console.log("filter unsuccessful");
      }
    }))
  }

}

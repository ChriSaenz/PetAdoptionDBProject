import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Pet } from '../../model/pet.model';
import { PetService } from '../../service/pet.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy {


  constructor() { }

  ngOnInit(): void {

  }

  ngOnDestroy(): void {
  }
}

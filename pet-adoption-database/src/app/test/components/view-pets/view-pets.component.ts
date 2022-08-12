import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { Pet } from 'src/app/model/pet.model';
import { PetService } from 'src/app/service/pet.service';

@Component({
  selector: 'app-view-pets',
  templateUrl: './view-pets.component.html',
  styleUrls: ['./view-pets.component.css']
})
export class ViewPetsComponent implements OnInit, OnDestroy {

  subscriptions: Subscription[] = []
  pets: Pet[] = []
  errorMsg: string = ""

  constructor(private petService: PetService) { }


  ngOnInit(): void {
    this.subscriptions.push(
      this.petService.getAllPets().subscribe({
        next: (data) => {
          this.pets = data
        },
        error: (e) => {
          console.log("[Test] Error when subscribing to pet$ from PetService in view-pets")
        }
      })
    )
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe())
  }
}

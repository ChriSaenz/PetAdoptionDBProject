import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { PetService } from 'app/service/pet.service';
import { Subscription } from 'rxjs';
import { Pet } from '../model/pet.model';

@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css'],
})
export class PetsComponent implements OnInit {
  subscriptions: Subscription[] = [];
  pets: Pet[] = [];
  errorMsg: string = '';
  opened: boolean;
  constructor(private petService: PetService) {}
  filterBreeds: FormControl;
  filterAges: FormControl;

  breedList: string[];
  ageList: string[];

  ngOnInit(): void {
    this.opened = true;

    this.filterBreeds = new FormControl('');
    this.breedList = ['Shiba', 'Bulldog', 'Poodle', 'Pug'];

    this.filterAges = new FormControl('');
    this.ageList = ['0', '1', '2', '3'];

    this.subscriptions.push(
      this.petService.getPets().subscribe({
        next: (data) => {
          this.pets = data;
        },
        error: (e) => {
          console.log(
            '[Test] Error when subscribing to pet$ from PetService in view-pets'
          );
        },
      })
    );
  }
}

import { Component, OnInit } from '@angular/core';
import { petsData } from '../data';
import { Pet } from '../model/pet.model';

@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css'],
})
export class PetsComponent implements OnInit {
  pets: Pet[];

  constructor() {}

  ngOnInit(): void {
    this.pets = petsData;
  }
}

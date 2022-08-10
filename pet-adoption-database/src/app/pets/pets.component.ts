import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { petsData } from '../data';
import { Pet } from '../model/pet.model';

@Component({
  selector: 'app-pets',
  templateUrl: './pets.component.html',
  styleUrls: ['./pets.component.css'],
})
export class PetsComponent implements OnInit {
  pets: Pet[];
  opened: boolean;
  constructor() {}
  filterBreeds: FormControl;
  filterAges: FormControl;

  breedList: string[];
  ageList: string[];

  ngOnInit(): void {
    this.pets = petsData;
    this.opened = false;

    this.filterBreeds = new FormControl('');
    this.breedList = ['Shiba', 'Bulldog', 'Poodle', 'Pug'];

    this.filterAges = new FormControl('');
    this.ageList = ['0', '1', '2', '3'];
  }
}

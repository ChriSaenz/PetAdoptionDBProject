import { Component, OnInit } from '@angular/core';
import { Pet } from '../../model/pet.model';

@Component({
  selector: 'app-view-pets',
  templateUrl: './view-pets.component.html',
  styleUrls: ['./view-pets.component.css']
})
export class ViewPetsComponent implements OnInit {

  pets: Pet[]
  constructor() { }

  ngOnInit(): void {
  }

}

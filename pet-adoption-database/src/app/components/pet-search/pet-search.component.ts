import { Component, OnInit } from '@angular/core';
import { Filter } from 'app/model/filter.model';
import { Pet } from 'app/model/pet.model';
import { PetService } from 'app/service/pet.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-pet-search',
  templateUrl: './pet-search.component.html',
  styleUrls: ['./pet-search.component.css']
})
export class PetSearchComponent implements OnInit {
  //  TODO: Insert link here for pet API
  public pets: Pet[]=[];
  public petsFiltered: Pet[]=[];

  constructor(private petService:PetService) { }

  ngOnInit(): void {
    this.petService.getAllPets().subscribe(data => {this.pets = data;});
    this.resetFilters();
  }

  //  Returns an array of filtered pets.
  searchForPets(filters:Filter) {
    //  For each property that exists in filters
    let arr = Object.getOwnPropertyNames(filters);
    arr.forEach(function (e) {
      //  filter array based on that filter
      let currentFilter = e as keyof typeof filters;
      this.petsFiltered.filter(f => f[currentFilter] == filters[currentFilter]);
    }); 
  }

  //  Pulls data from database into pets, which also resets filters
  resetFilters(): void {
    this.petsFiltered = Array.from(this.pets);
  }
}

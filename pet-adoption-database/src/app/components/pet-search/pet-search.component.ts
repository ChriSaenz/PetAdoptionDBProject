import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Filter } from 'src/app/model/filter.model';
import { Pet } from 'src/app/model/pet.model';
import { PetService } from 'src/app/service/pet.service';

@Component({
  selector: 'app-pet-search',
  templateUrl: './pet-search.component.html',
  styleUrls: ['./pet-search.component.css']
})
export class PetSearchComponent implements OnInit {
  //  TODO: Insert link here for pet API
  public pets: Pet[]=[];

  constructor(private petService:PetService) { }

  ngOnInit(): void {
    this.resetFilters();
  }

  //  Returns an array of filtered pets.
  searchForPets(filters:Filter): Observable<Pet> {
    //  Copy pets into new array
    let petsFiltered = Array.from(this.pets);

    //  For each property that exists in filters
    let arr = Object.getOwnPropertyNames(filters);
    arr.forEach(function (e) {
      //  filter array based on that filter
      let currentFilter = e as keyof typeof filters;
      petsFiltered.filter(f => f[currentFilter] == filters[currentFilter]);
    }); 
  }

  //  Pulls data from database into pets, which also resets filters
  resetFilters(): void {
    this.petService.getPets().subscribe(data => {this.pets = data;});
  }
}

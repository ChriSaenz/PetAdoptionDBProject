import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthService } from 'app/auth/service/auth.service';
import { Filter } from 'app/model/filter.model';
import { Pet } from 'app/model/pet.model';
import { PetService } from 'app/service/pet.service';

@Component({
  selector: 'app-pet-search',
  templateUrl: './pet-search.component.html',
  styleUrls: ['./pet-search.component.css']
})
export class PetSearchComponent implements OnInit {
  //  TODO: Insert link here for pet API
  pets:Pet[]=[];
  petsFiltered:Pet[];
  filterForm:FormGroup;
  colorsUnique:string[]=[];
  speciesUnique:string[]=[];
  breedUnique:string[]=[];

  constructor(private petService:PetService, private authService:AuthService) { }

  ngOnInit(): void {
    //  pull data from pets API
    this.petService.getAllPets().subscribe({
      next: (data) => {
        console.log("Successfully returned " + data.length + " pets from DB");
        this.pets = data;
        this.resetFilters();
        //  add filter options given pet data
        this.speciesUnique = [null];
        this.colorsUnique = [null];
        this.breedUnique = [null];
        this.pets.forEach(e => {
          if(this.speciesUnique.indexOf(e.species) == -1) {
            this.speciesUnique.push(e.species);
          }
          if(this.colorsUnique.indexOf(e.color) == -1) {
            this.colorsUnique.push(e.color);
          }
          if(this.breedUnique.indexOf(e.breed) == -1) {
            this.breedUnique.push(e.breed);
          }
        });
      },
      error: (e) => {console.log("Error returned at resetFilters in pet-search.component.ts:57");}
    });

    //  instantiate filterForm
    this.filterForm = new FormGroup({
      name: new FormControl(''),
      species: new FormControl(''),
      age: new FormControl(''),
      sex: new FormControl(''),
      color: new FormControl(''),
      breed: new FormControl(''),
      vaccinated: new FormControl(''),
      neutered: new FormControl(''),
    });
  }

  //  Filters pets in array.
  searchForPets(): void {
    //  Create new filter with form data
    let filters = new Filter();
    filters.name = this.filterForm.value.name;
    filters.species = this.filterForm.value.species;
    filters.age = this.filterForm.value.age;
    filters.sex = this.filterForm.value.sex;
    filters.color = this.filterForm.value.color;
    filters.breed = this.filterForm.value.breed;
    filters.vaccinated = this.filterForm.value.vaccinated;
    filters.neutered = this.filterForm.value.neutered;

    //  For each property that exists in filters
    Object.entries(filters).forEach(([property, value]) => {
      //  filter array based on that filter if a valid value exists for it
      if(value != null && value != '' && value != "null" && value != undefined) {
        console.log("Applying filter " + property + ":" + value);
        // this.petsFiltered = this.petsFiltered.filter(f => {f[property] == value});
        this.petService.getPetsByFilter(property, value).subscribe({
          next: (data) => {this.petsFiltered = data},
          error: (e) => {console.log("Error returned at searchForPets() in pet-search.component.ts:84");}
        });
      }
    });
  }

  //  resets filtered pets displayed: assigns
  resetFilters(): void {
    this.petsFiltered = this.pets;
  }

  matchesFilters() {

  }

  //  For use with the Adopt button
  userIsLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }
}
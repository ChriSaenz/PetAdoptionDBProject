import { Component, OnInit } from '@angular/core';
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
  pets: Pet[]=[];
  colorsUnique:string[]=[];
  speciesUnique:string[]=[];
  breedUnique:string[]=[];

  constructor(private petService:PetService, private authService:AuthService) { }

  ngOnInit(): void {
    this.resetFilters();
  }

  //  Filters pets in array.
  searchForPets(): void {
    this.resetFilters();

    //  Create new filter with form data
    let filters = new Filter();
    filters.name = (document.getElementById("name") as HTMLInputElement).value;
    filters.species = (document.getElementById("species") as HTMLInputElement).value;
    filters.age = parseInt((document.getElementById("age") as HTMLInputElement).value);
    // filters.date_acquired = (document.getElementById("date_acquired") as HTMLInputElement).value;
    filters.sex = (document.getElementById("sex") as HTMLInputElement).value;
    filters.color = (document.getElementById("color") as HTMLInputElement).value;
    filters.breed = (document.getElementById("breed") as HTMLInputElement).value;
    filters.vaccinated = (document.getElementById("vaccinated") as HTMLInputElement).value == "true" ? true : false;
    filters.neutered = (document.getElementById("neutered") as HTMLInputElement).value == "true" ? true : false;
    
    filters.cost = parseFloat((document.getElementById("cost") as HTMLInputElement).value);

    //  For each property that exists in filters
    let arr = Object.getOwnPropertyNames(filters);
    arr.forEach(function (e) {
      //  filter array based on that filter
      let currentFilter = e as keyof typeof filters;
      this.pets.filter(f => f[currentFilter] == filters[currentFilter]);
    });
  }

  //  Pulls data from database into pets and filter options into filters
  resetFilters(): void {
    //  pull data from pets API
    this.petService.getPets().subscribe(data => {this.pets = data;});
    //  add filter options given pet data: species
    this.speciesUnique = [];
    this.pets.forEach(e => {
      if(this.speciesUnique.indexOf(e.species) == -1) {
        this.speciesUnique.concat(e.species);
      }
    });
    //  add filter options given pet data: color
    this.colorsUnique = [];
    this.pets.forEach(e => {
      if(this.colorsUnique.indexOf(e.color) == -1) {
        this.colorsUnique.concat(e.color);
      }
    });
    //  add filter options given pet data: breed
    this.breedUnique = [];
    this.pets.forEach(e => {
      if(this.breedUnique.indexOf(e.breed) == -1) {
        this.breedUnique.concat(e.breed);
      }
    });
  }

  //  Toggles visibility of additional information in a column for pet
  toggleInfo(id:number): void {

  }

  //  For use with the Adopt button
  userIsLoggedIn(): boolean {
    return this.authService.isLoggedIn();
  }

  // //   Material Dialog
  // openAdoptionDialog(p:Pet): void {
  //   this.adoptDialog.
  // }
}

// @Component({
//   templateUrl: "adopt-dialog.component.html"
// })
// export class adoptionDialog {
//   public p: Pet;

//   constructor() {}

//   closeDialog(): void {

//   }
//   createRequestForPet(p:Pet): void {

//   }
// }
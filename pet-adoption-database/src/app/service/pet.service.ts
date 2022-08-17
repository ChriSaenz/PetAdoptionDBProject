import { HttpClient } from '@angular/common/http';
import { getSafePropertyAccessString } from '@angular/compiler';
import { Injectable } from '@angular/core';

import { BehaviorSubject, Observable } from 'rxjs';

import { Pet } from '../model/pet.model';

@Injectable({
  providedIn: 'root'
})
export class PetService {
  //  change these to be whatever the URL for the pet search is
  postApi:string = "http://localhost:8824/pet"
  getApi:string = "http://localhost:8824/pet"
  pet$ = new BehaviorSubject<Pet[]>([]);

  constructor(private http:HttpClient) {}

  public postPet(pet:Pet): Observable<Pet> {
    return this.http.post<Pet>(this.postApi, pet)
  }

  getAllPets(): Observable<Pet[]> {
    let gotPets: Observable<Pet[]> = this.http.get<Pet[]>(this.getApi);
    return gotPets;
  }

  //  Filter methods
  getPetsByFilter(filter:string, value:string): Observable<Pet[]> {
    return this.http.get<Pet[]>(this.getApi + "/" + filter + "/" + value);
  }
}
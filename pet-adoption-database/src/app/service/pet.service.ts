import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'environments/environment';

import { BehaviorSubject, Observable } from 'rxjs';

import { Pet } from '../model/pet.model';

@Injectable({
  providedIn: 'root',
})
export class PetService {
  getAllPetsApi: string;
  postPetApi: string;
  pet$ = new BehaviorSubject<Pet[]>([]);

  constructor(private http: HttpClient) {
    this.getAllPetsApi = environment.serverURL + '/pet';
    this.postPetApi = environment.serverURL + '/pet';
  }

  getAllPets(): Observable<Pet[]> {
    return this.http.get<Pet[]>(this.getAllPetsApi);
  }

  postPet(pet: Pet): Observable<Pet> {
    return this.http.post<Pet>(this.postPetApi, pet);
  }
}

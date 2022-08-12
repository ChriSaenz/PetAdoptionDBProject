import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Pet } from '../model/pet.model';

@Injectable({
  providedIn: 'root'
})
export class PetService {
  getAllPetsApi: string
  postPetApi: string
  pet$ = new BehaviorSubject<Pet[]>([])
  

  constructor(private http: HttpClient) {
    this.getAllPetsApi = environment.serverURL + "/pet"
    this.postPetApi = environment.serverURL + "/pet"
  }

  getAllPets(): Observable<Pet[]>
  {
    return this.http.get<Pet[]>(this.getAllPetsApi)
  }

  postPet(pet: Pet): Observable<Pet>
  {
    return this.http.post<Pet>(this.postPetApi, pet)
  }
}

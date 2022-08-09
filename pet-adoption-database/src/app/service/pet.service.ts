import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pet } from '../model/pet.model';

@Injectable({
  providedIn: 'root'
})
export class PetService {
  //  change these to be whatever the URL for the pet search is
  postApi:string = "http://localhost:7555/pets"
  getApi:string = "http://localhost:7555/pets"

  constructor(private http:HttpClient) {}

  public postPet(pet:Pet): Observable<Pet> {
    return this.http.post<Pet>(this.postApi, Pet)
  }

  getPets(): Observable<Pet[]> {
    return this.http.get<Pet[]>(this.getApi);
  }
}

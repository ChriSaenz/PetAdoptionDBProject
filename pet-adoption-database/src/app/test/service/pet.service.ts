import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { Pet } from '../model/pet.model';

@Injectable({
  providedIn: 'root'
})
export class PetService {

  getAllApi: string;
  pet$ = new BehaviorSubject<Pet[]>([])
  
  constructor(private http: HttpClient) {
    this.getAllApi = "http://localhost:8824/pet"
  }

  getAllPets(): Observable<Pet[]>
  {
    return this.http.get<Pet[]>(this.getAllApi)
  }
}

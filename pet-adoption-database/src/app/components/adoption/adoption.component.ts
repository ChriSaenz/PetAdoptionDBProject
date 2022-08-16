import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'app/auth/service/auth.service';
import { Pet } from 'app/model/pet.model';
import { PetService } from 'app/service/pet.service';
import { PetRequest } from '../requests/model/petrequest.model';
import { RequestService } from '../requests/service/request.service';

@Component({
  selector: 'app-adoption',
  templateUrl: './adoption.component.html',
  styleUrls: ['./adoption.component.css']
})
export class AdoptionComponent implements OnInit {
  petToAdopt: Pet;

  constructor(private petService:PetService,
    private activatedRoute:ActivatedRoute, private requestService:RequestService,
    private authService:AuthService) { }

  //  Gets pet based on target Id retrieved from URL
  ngOnInit(): void {
    let targetId = parseInt(this.activatedRoute.snapshot.paramMap.get('id'));
    this.petService.getPets().subscribe(
      e => {e.forEach(
        f => {if(f.id == targetId) {
          this.petToAdopt = f;;
          return;
        }
      });}
    );
  }

  //  Get customer ID from ??? and create a post request with it
  createRequest(): void {
    //  Get user information from username


    //  Fill out request details
    let pr = new PetRequest();
    pr.date = new Date().toDateString();
    pr.status  = "Pending";
    // pr.c_id = 
    pr.c_name = this.authService.username$.getValue();
    // pr.c_phone = 
    // pr.c_date_joined = 
    // pr.c_birthday = 
    pr.p_id = this.petToAdopt.id;
    pr.p_name = this.petToAdopt.name;
    pr.p_species = this.petToAdopt.species;
    pr.p_age = this.petToAdopt.age;
    pr.p_date_acquired = this.petToAdopt.date_acquired.toDateString();
    pr.p_sex = this.petToAdopt.sex;
    pr.p_color = this.petToAdopt.color;
    pr.p_breed = this.petToAdopt.breed;
    pr.p_vaccinated = this.petToAdopt.vaccinated;
    pr.p_neutered = this.petToAdopt.neutered;
    pr.p_cost = this.petToAdopt.cost;
    
    this.requestService.postRequest(pr, null, pr.c_id, pr.p_id);
  }
}

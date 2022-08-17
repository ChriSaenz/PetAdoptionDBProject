import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'app/auth/service/auth.service';
import { Pet } from 'app/model/pet.model';
import { User } from 'app/model/user.model';
import { PetService } from 'app/service/pet.service';
import { UserService } from 'app/service/user.service';
import { PetRequest } from '../requests/model/petrequest.model';
import { RequestService } from '../requests/service/request.service';

@Component({
  selector: 'app-adoption',
  templateUrl: './adoption.component.html',
  styleUrls: ['./adoption.component.css']
})
export class AdoptionComponent implements OnInit {
  petToAdopt:Pet = new Pet();
  adopter:User = new User();

  constructor(private petService:PetService,
    private activatedRoute:ActivatedRoute, private requestService:RequestService,
    private authService:AuthService, private userService:UserService) { }

  //  Gets pet based on target Id retrieved from URL
  ngOnInit(): void {
    let targetId = parseInt(this.activatedRoute.snapshot.paramMap.get('petId'));
    this.petService.getPetById(targetId).subscribe({
      next: (data) => {
        console.log("Successfully returned pet " + data.name);
        this.petToAdopt = data;
      },
      error: (e) => {console.log("Error returned at ngOnInit() in adoption.component.ts:32");}
    });
    
    let username = localStorage.getItem('username');
    this.userService.getUserByUsername(username).subscribe({
      next: (data) => {
        this.adopter = data;
        console.log("Successfully returned user " + this.adopter.nickname + " from username " + username);
      },
      error: (e) => {console.log("Error returned at ngOnInit() in adoption.component.ts:40");}
    });
  }

  //  Create a post request with pet and customer information
  createRequest(): void {
    let pr = new PetRequest();
    pr.date = null;
    pr.status  = "Pending";
    pr.c_id = this.adopter.id;
    pr.c_name = this.adopter.nickname;
    // pr.c_phone = "";
    // pr.c_date_joined = "";
    // pr.c_birthday = "";
    pr.p_id = this.petToAdopt.id;
    pr.p_name = this.petToAdopt.name;
    pr.p_species = this.petToAdopt.species;
    pr.p_age = this.petToAdopt.age;
    pr.p_date_acquired = this.petToAdopt.date_acquired;
    pr.p_sex = this.petToAdopt.sex;
    pr.p_color = this.petToAdopt.color;
    pr.p_breed = this.petToAdopt.breed;
    pr.p_vaccinated = this.petToAdopt.vaccinated;
    pr.p_neutered = this.petToAdopt.neutered;
    pr.p_cost = this.petToAdopt.cost;
    
    //  make new request and push to DB
    //  TODO: Change 1 to a value that shows no Employee ID
    this.requestService.postRequest(pr, 1, pr.c_id, pr.p_id).subscribe({
      next: (data) => {
        pr = data;
        //  POST doesn't update the database, must do this to update database
        let reqArr = this.requestService.request$.getValue();
        reqArr.push(pr);
        this.requestService.request$.next(reqArr);
        console.log("Successfully created new request");
      },
      error: (e) => {console.log("Error returned at ngOnInit() in adoption.component.ts:77");}
    });
  }
}

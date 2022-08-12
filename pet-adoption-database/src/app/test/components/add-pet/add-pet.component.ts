import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Pet } from 'src/app/model/pet.model';
import { PetService } from 'src/app/service/pet.service';

@Component({
  selector: 'app-add-pet',
  templateUrl: './add-pet.component.html',
  styleUrls: ['./add-pet.component.css']
})
export class AddPetComponent implements OnInit, OnDestroy {

  subscriptions: Subscription[] = []
  credentials: string
  pet: Pet
  newPetForm: FormGroup
  username: string
  msg: string

  constructor(private petService: PetService, private authService: AuthService) { }

  ngOnInit(): void {
    this.newPetForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      age: new FormControl('', [Validators.required]),
      species: new FormControl('', [Validators.required]),
      breed: new FormControl('', [Validators.required]),
      color: new FormControl('', [Validators.required]),
      sex: new FormControl('', [Validators.required]),
      neutered: new FormControl(false),
      vaccinated: new FormControl(false),
      cost: new FormControl('', [Validators.required]),
      imagePath: new FormControl('')
    })
  }

  onFormSubmit() {
    //Getting only the image name for imagePath
    let path = this.newPetForm.get('imagePath').value
    let splittedPath = path.split("\\")

    this.newPetForm.value.imagePath = splittedPath[splittedPath.length - 1]

    this.pet = this.newPetForm.value

    this.petService.postPet(this.pet).subscribe({
      next: (data) => {
        this.pet = data
        this.msg = "[AddPetComponent] Pet added to db"

        let petArr = this.petService.pet$.getValue()

        petArr.push(this.pet)

        this.petService.pet$.next(petArr)

        //stat
      },
      error: (e) =>{
        console.log("[AddPet-onFormSubmit] Error when trying to add pet")
      }
    })

    //     error: (e) => {this.authService.message$.next("Invalid Credentials")}
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe())
  }
}

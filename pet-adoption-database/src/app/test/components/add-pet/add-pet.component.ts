import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/service/auth.service';
import { Pet } from '../../model/pet.model';
import { PetService } from '../../service/pet.service';

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
      name: new FormControl(''),
      age: new FormControl(''),
      species: new FormControl(''),
      breed: new FormControl(''),
      color: new FormControl(''),
      sex: new FormControl(''),
      neutered: new FormControl(''),
      vaccinated: new FormControl(''),
      cost: new FormControl('')
    })
  }

  onFormSubmit() {
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

    // this.username = this.loginForm.value.username
    // this.password = this.loginForm.value.password

    // this.subscriptions.push(
    //   this.authService.login(this.username, this.password).subscribe({
    //     next: (data) => {
    //       this.user = data
    //       localStorage.setItem('username', this.user.username)
    //       localStorage.setItem('credentials', btoa(this.username + ':' + this.password))
    //       this.authService.username$.next(this.user.username)
    //       this.router.navigateByUrl('/dashboard')
    //     },
    //     error: (e) => {
    //       this.authService.message$.next("Invalid Credentials")
    //     }
    //   })
    // )
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(s => s.unsubscribe())
  }
}

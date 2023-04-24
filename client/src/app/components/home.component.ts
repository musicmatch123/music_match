import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { lastValueFrom } from 'rxjs';
import { LoginService } from '../services/login.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {


  loginForm!: FormGroup;

  constructor(private httpClient: HttpClient, private fb: FormBuilder, private loginSvc: LoginService) {}


  ngOnInit(): void {
    this.loginForm= this.createLoginForm();
  }

  createLoginForm(): FormGroup {
    return this.fb.group({
      username: this.fb.control('',[Validators.required]),
      dob: this.fb.control('', [Validators.required])
    });
  }

  login() {
    
    console.log(this.loginForm.value)
    const username = this.loginForm.get("username")?.value;
    // const dob = this.loginForm.get("dob")?.value;
    
    //spotify OAuth
    return lastValueFrom(this.httpClient.get( environment.apiBaseUrl+ '/login', {responseType: 'text'}))
      .then((res) => {
        console.log(res)
        localStorage.setItem('user', username);
        window.location.href=res; // navigate to spotify authentication page
      }) 
    .catch(err => console.error('ERROR >>> ',err))

    


  }

  createUser(){
    const username = this.loginForm.get("username")?.value;
    const dob = this.loginForm.get("dob")?.value;

    // create user 
    this.loginSvc.createUser(username, dob).then(response => {
      console.log(response);
    }).catch(error=> {
      console.error(error);
    })
  }

}

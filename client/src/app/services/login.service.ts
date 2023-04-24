import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { lastValueFrom } from 'rxjs';
import { environment } from "src/environments/environment";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private httpClient: HttpClient) { }

  createUser(username: string, dob: string): Promise<string> {

    console.info(username)
    console.info(dob)
    const payload = {
      username: username,
      dob: dob
    }
    return lastValueFrom(this.httpClient.post<string>( environment.apiBaseUrl + '/register', payload))
    .then(result => {
      return result
    });
  }

}

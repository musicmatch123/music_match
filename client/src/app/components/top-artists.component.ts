import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { lastValueFrom, map, pipe } from 'rxjs';
import { Artist } from '../models/artist.model';
import { Router } from '@angular/router';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-top-artists',
  templateUrl: './top-artists.component.html',
  styleUrls: ['./top-artists.component.scss']
})
export class TopArtistsComponent implements OnInit {

  artists: Artist[] =  []

  constructor(private httpClient: HttpClient, public router: Router) {}

  ngOnInit(): void {
      this.getTopArtists()
  }

  getTopArtists() {
    lastValueFrom(this.httpClient.get<Artist[]>( environment.apiBaseUrl+ '/user-top-artists'))
      .then((res) => {
        console.log(res)
        this.artists = res 
      })
    .catch(err => console.error(err))
  }

  openChat() {
    debugger;
    this.router.navigate(['/chat', { state: { username: 'syaz' } }]);
  }
}

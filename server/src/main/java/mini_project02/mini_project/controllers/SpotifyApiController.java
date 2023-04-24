package mini_project02.mini_project.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.personalization.simplified.GetUsersTopArtistsRequest;

import static mini_project02.mini_project.controllers.AuthController.spotifyApi;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class SpotifyApiController {

    @GetMapping(value = "user-top-artists")
    public Artist[] getUserToArtists(){
        
        final GetUsersTopArtistsRequest getUsersTopArtistsRequest = spotifyApi.getUsersTopArtists()
                .time_range("medium_term")
                .limit(10)
                .offset(0)
                .build();

        try{
            final Paging<Artist> artistPaging =getUsersTopArtistsRequest.execute();

            //return as JSON 
            return artistPaging.getItems();

        }catch(Exception e){
            System.out.println("Something went wrong!\n" +e.getMessage());;
        }
        return new Artist[0];
}
    }
    

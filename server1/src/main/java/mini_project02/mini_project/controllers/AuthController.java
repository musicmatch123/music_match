package mini_project02.mini_project.controllers;

import java.io.IOException;
import java.net.URI;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin(origins="*")
@RequestMapping("/api")
public class AuthController {

    //TODO: must change the api URI in here and spotify app
    private static final String CLIENT_ID = "15a30563c68e414ca7d5e8a12baabc1b";
    private static final String CLIENT_SECRET = "662b61c45fcf42c38a5fbb87b9a25212";
    
  private static final URI redirectUri = SpotifyHttpManager.makeUri( "https://dainty-tray-production.up.railway.app/api/get-user");

 //  private static final URI redirectUri = SpotifyHttpManager.makeUri( "http://localhost:8080/api/get-user");


    public String code = "";

    public static final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .setRedirectUri(redirectUri)
            .build();
    //private static final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code);

    @GetMapping("/login")
    @ResponseBody
    public String spotifyLogin(){
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
            .scope("user-read-private, user-read-email, user-top-read")
            .show_dialog(true)
            .build();
        final URI uri = authorizationCodeUriRequest.execute();
    return uri.toString();

    }

    @GetMapping("/get-user")
    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException{
        code = userCode;
        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(code)
                .build();
        
        try{
            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            // Set Access and refresh token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken());
            
        }catch(IOException | SpotifyWebApiException | org.apache.hc.core5.http.ParseException e){
            System.out.println("Error: " + e.getMessage());
        }
      response.sendRedirect("https://dainty-tray-production.up.railway.app/#/user-top-artists");
      //  response.sendRedirect("http://localhost:4200/#/user-top-artists");

        return userCode;
    }
}

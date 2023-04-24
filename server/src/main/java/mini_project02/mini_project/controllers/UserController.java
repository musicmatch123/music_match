package mini_project02.mini_project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.util.MultiValueMap;

import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import mini_project02.mini_project.repositories.UserRepositories;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(path = "/api", 
produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired 
    private UserRepositories userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    //postmapping cause insert the username and DOB 
    @PostMapping(path="/register")
    public ResponseEntity<String> registerUser (@RequestBody String payload){

        logger.info(payload);
        System.out.println("payload > " + payload);
        
        // //create username and input DOB from form
        // String username = form.getFirst("username");
        // String dateOfBirth = form.getFirst("dateOfBirth");

        // Users user = new Users();
        // user.setUsername(username);
        // user.setDateOfBirth(dateOfBirth);

        //username and DOB to JSON 

        //get username from request body 
        JsonReader reader = Json.createReader(new StringReader(payload));
        JsonObject json = reader.readObject();
        String username = json.getString("username");
        String dob = json.getString("dob");

        System.out.println("before creating");

        Integer isCreated = userRepo.createUser(username, dob);

        System.out.println("aftr creating > " + (isCreated > 0));


        return ResponseEntity.ok("user created successfully");

    }
}

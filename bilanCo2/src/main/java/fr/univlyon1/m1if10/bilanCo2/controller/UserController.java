package fr.univlyon1.m1if10.bilanCo2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import fr.univlyon1.m1if10.bilanCo2.model.Users;
import fr.univlyon1.m1if10.bilanCo2.repository.UserRepository;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

//@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all users in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    })
            })
    public ResponseEntity<List<Users>> getAllUser(@RequestParam(required = false) String title) {
        try {
            List<Users> users = new ArrayList<Users>();

            if (title == null)
                userRepository.findAll().forEach(users::add);
            else
                userRepository.findByNameContaining(title).forEach(users::add);

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Get one user in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request", content = @Content())
            })
    public ResponseEntity<Users> getUserById(@PathVariable("id") long id) {
        Optional<Users> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    @Operation(summary = "Create a user",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    public ResponseEntity<Users> createUser(@RequestBody Users users) {
        try {
            Users _users = userRepository.save(new Users(users.getName(), users.getMail()));
            System.out.println("creating users...");
            return new ResponseEntity<>(_users, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

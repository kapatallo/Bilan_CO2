package fr.univlyon1.m1if10.bilanCo2.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import fr.univlyon1.m1if10.bilanCo2.model.Utilisateur;
import fr.univlyon1.m1if10.bilanCo2.repository.UtilisateurRepository;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

//@CrossOrigin(origins = "http://localhost:8080")
@Controller
@RequestMapping("/utilisateur")
public class UtilisateurController {

    Logger logger = Logger.getLogger(getClass().getName());

    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public UtilisateurController(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all Utilisateur in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    })
            })
    public ResponseEntity<List<Utilisateur>> getAllUser() {
        try {
            return new ResponseEntity<>(utilisateurRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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
    public ResponseEntity<Utilisateur> getUserById(@PathVariable("id") long id) {
        Optional<Utilisateur> userData = utilisateurRepository.findById(id);

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
    public ResponseEntity<Utilisateur> createUser(@RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur resUsers = utilisateurRepository.save(new Utilisateur(utilisateur.getName(), utilisateur.getMail()));
            logger.info("creating Utilisateur...");
            return new ResponseEntity<>(resUsers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

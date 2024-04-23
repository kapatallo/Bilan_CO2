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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Utilisateur controller.
 */
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    /**
     * The Logger.
     */
    private final Logger logger = Logger.getLogger(getClass().getName());

    private UtilisateurRepository utilisateurRepository;

    /**
     * Instantiates a new Utilisateur controller.
     *
     * @param utilisateurRepository the utilisateur repository
     */
    @Autowired
    public UtilisateurController(final UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Gets all user.
     *
     * @return the all user
     */
    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all Utilisateur in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<List<Utilisateur>> getAllUser() {
        try {
            return new ResponseEntity<>(utilisateurRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Get one user in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Utilisateur> getUserById(@PathVariable("id") final long id) {
        Optional<Utilisateur> userData = utilisateurRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create user response entity.
     *
     * @param utilisateur the utilisateur
     * @return the response entity
     */
    @PostMapping("/")
    @Operation(summary = "Create a user",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Utilisateur> createUser(@RequestBody final Utilisateur utilisateur) {
        try {
            Utilisateur resUsers = utilisateurRepository.save(new Utilisateur(utilisateur));
            logger.info("creating Utilisateur...");
            return new ResponseEntity<>(resUsers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

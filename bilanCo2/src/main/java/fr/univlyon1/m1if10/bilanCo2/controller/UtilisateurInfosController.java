package fr.univlyon1.m1if10.bilanCo2.controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import fr.univlyon1.m1if10.bilanCo2.model.UtilisateurInfos;
import fr.univlyon1.m1if10.bilanCo2.repository.UtilisateurInfosRepository;
import io.swagger.v3.oas.annotations.media.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type UtilisateurInfos controller.
 */
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000", "http://localhost",
                        "https://192.168.75.51/api", "http://192.168.75.51",
                        "https://192.168.75.51"})
@RestController
@RequestMapping("/utilisateur/infos")
public class UtilisateurInfosController {

    /**
     * The Logger.
     */
    private final Logger logger = Logger.getLogger(getClass().getName());

    private UtilisateurInfosRepository utilisateurInfosRepository;

    /**
     * Instantiates a new UtilisateurInfos controller.
     *
     * @param utilisateurInfosRepository the utilisateurInfos repository
     */
    @Autowired
    public UtilisateurInfosController(final UtilisateurInfosRepository utilisateurInfosRepository) {
        this.utilisateurInfosRepository = utilisateurInfosRepository;
    }

    /**
     * Gets all user infos.
     *
     * @return the all user infos
     */
    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all UtilisateurInfos in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<List<UtilisateurInfos>> getAllUserInfos() {
        try {
            return new ResponseEntity<>(utilisateurInfosRepository.findAll(), HttpStatus.OK);
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
    @Operation(summary = "Get one userInfos in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<UtilisateurInfos> getUserInfosById(@PathVariable("id") final long id) {
        Optional<UtilisateurInfos> userData = utilisateurInfosRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create user response entity.
     *
     * @param utilisateurInfos the utilisateurInfos
     * @return the response entity
     */
    @PostMapping("/")
    @Operation(summary = "Create a user",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<UtilisateurInfos> createUser(@RequestBody final
                                                           UtilisateurInfos utilisateurInfos) {
        try {
            UtilisateurInfos resUsers = utilisateurInfosRepository.save(new
                    UtilisateurInfos(utilisateurInfos));
            logger.info("creating UtilisateurInfos...");
            return new ResponseEntity<>(resUsers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

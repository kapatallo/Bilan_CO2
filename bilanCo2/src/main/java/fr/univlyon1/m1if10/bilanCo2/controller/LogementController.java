package fr.univlyon1.m1if10.bilanCo2.controller;

import fr.univlyon1.m1if10.bilanCo2.model.Ident;
import fr.univlyon1.m1if10.bilanCo2.model.Logement;
import fr.univlyon1.m1if10.bilanCo2.repository.LogementRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * The type Logement controller.
 */
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000","http://localhost",
                        "https://192.168.75.51/api", "http://192.168.75.51",
                        "https://192.168.75.51"})
@RestController
@RequestMapping("/utilisateur/questionnaireHebdo/logement")
public class LogementController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private LogementRepository logementRepository;

    /**
     * Instantiates a new Logement controller.
     *
     * @param logementRepository the logement repository
     */
    @Autowired
    public LogementController(final LogementRepository logementRepository) {
        this.logementRepository = logementRepository;
    }

    /**
     * Gets all user logement.
     *
     * @return the all user logement
     */
    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all Logement in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<List<Logement>> getAllUserLogement() {
        try {
            return new ResponseEntity<>(logementRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets user logement by id.
     *
     * @param ident the ident
     * @return the user logement by id
     */
    @GetMapping(value = "/{ident}", produces = {"application/json"})
    @Operation(summary = "Get one userLogement in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Logement> getUserLogementById(@PathVariable("ident") final Ident ident) {
        Optional<Logement> userLogementData = logementRepository.findByIdent(ident.getAnnee(),
                ident.getSemaine(), ident.getId());

        if (userLogementData.isPresent()) {
            return new ResponseEntity<>(userLogementData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create user response entity.
     *
     * @param logement the logement
     * @return the response entity
     */
    @PostMapping("/")
    @Operation(summary = "Create a userLogement",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Logement> createUser(@RequestBody final
                                                           Logement logement) {
        try {
            Logement resLogement = logementRepository.save(new
                    Logement(logement));
            logger.info("creating Logement...");
            return new ResponseEntity<>(resLogement, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

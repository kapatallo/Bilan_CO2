package fr.univlyon1.m1if10.bilanCo2.controller;

import fr.univlyon1.m1if10.bilanCo2.model.Divers;
import fr.univlyon1.m1if10.bilanCo2.model.Ident;
import fr.univlyon1.m1if10.bilanCo2.repository.DiversRepository;
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

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * The type Divers controller.
 */
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/utilisateur/questionnaireHebdo/divers")
public class DiversController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private DiversRepository diversRepository;

    /**
     * Instantiates a new Divers controller.
     *
     * @param diversRepository the divers repository
     */
    @Autowired
    public DiversController(final DiversRepository diversRepository) {
        this.diversRepository = diversRepository;
    }

    /**
     * Gets all user divers.
     *
     * @return the all user divers
     */
    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all Divers in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<List<Divers>> getAllUserDivers() {
        try {
            return new ResponseEntity<>(diversRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets user divers by id.
     *
     * @param ident the ident
     * @return the user divers by id
     */
    @GetMapping(value = "/{ident}", produces = {"application/json"})
    @Operation(summary = "Get one userDivers in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Divers> getUserDiversById(@PathVariable("ident") final Ident ident) {
        Optional<Divers> userDiversData = diversRepository.findByIdent(ident.getAnnee(), ident.getSemaine(), ident.getId());

        if (userDiversData.isPresent()) {
            return new ResponseEntity<>(userDiversData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create user response entity.
     *
     * @param divers the divers
     * @return the response entity
     */
    @PostMapping("/")
    @Operation(summary = "Create a userDivers",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Divers> createUser(@RequestBody final
                                             Divers divers) {
        try {
            Divers resDivers = diversRepository.save(new
                    Divers(divers));
            logger.info("creating Divers...");
            return new ResponseEntity<>(resDivers, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

package fr.univlyon1.m1if10.bilanCo2.controller;

import fr.univlyon1.m1if10.bilanCo2.model.Alimentation;
import fr.univlyon1.m1if10.bilanCo2.model.Ident;
import fr.univlyon1.m1if10.bilanCo2.repository.AlimentationRepository;
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
 * The type Alimentation controller.
 */
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/utilisateur/questionnaireHebdo/alimentation")
public class AlimentationController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private AlimentationRepository alimentationRepository;

    /**
     * Instantiates a new Alimentation controller.
     *
     * @param alimentationRepository the alimentation repository
     */
    @Autowired
    public AlimentationController(final AlimentationRepository alimentationRepository) {
        this.alimentationRepository = alimentationRepository;
    }

    /**
     * Gets all user alimentation.
     *
     * @return the all user alimentation
     */
    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all Alimentation in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<List<Alimentation>> getAllUserAlimentation() {
        try {
            return new ResponseEntity<>(alimentationRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets user alimentation by id.
     *
     * @param ident the ident
     * @return the user alimentation by id
     */
    @GetMapping(value = "/{ident}", produces = {"application/json"})
    @Operation(summary = "Get one userAlimentation in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Alimentation> getUserAlimentationById(@PathVariable("ident") final Ident ident) {
        Optional<Alimentation> userAlimentationData = alimentationRepository.findByIdent(ident.getAnnee(), ident.getSemaine(), ident.getId());

        if (userAlimentationData.isPresent()) {
            return new ResponseEntity<>(userAlimentationData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create user response entity.
     *
     * @param alimentation the alimentation
     * @return the response entity
     */
    @PostMapping("/")
    @Operation(summary = "Create a userAlimentation",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Alimentation> createUser(@RequestBody final
                                                           Alimentation alimentation) {
        try {
            Alimentation resAlimentation = alimentationRepository.save(new
                    Alimentation(alimentation));
            logger.info("creating Alimentation...");
            return new ResponseEntity<>(resAlimentation, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

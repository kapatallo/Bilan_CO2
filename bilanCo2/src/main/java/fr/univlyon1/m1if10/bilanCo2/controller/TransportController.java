package fr.univlyon1.m1if10.bilanCo2.controller;

import fr.univlyon1.m1if10.bilanCo2.model.Transport;
import fr.univlyon1.m1if10.bilanCo2.repository.TransportRepository;
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
 * The type Transport controller.
 */
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/utilisateur/questionnaireHebdo/transport")
public class TransportController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private TransportRepository transportRepository;

    /**
     * Instantiates a new Transport controller.
     *
     * @param transportRepository the transport repository
     */
    @Autowired
    public TransportController(final TransportRepository transportRepository) {
        this.transportRepository = transportRepository;
    }

    /**
     * Gets all user transport.
     *
     * @return the all user transport
     */
    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all Transport in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<List<Transport>> getAllUserTransport() {
        try {
            return new ResponseEntity<>(transportRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets user transport by id.
     *
     * @param id the id
     * @return the user transport by id
     */
    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Get one userTransport in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Transport> getUserTransportById(@PathVariable("id") final long id) {
        Optional<Transport> userTransportData = transportRepository.findById(id);

        if (userTransportData.isPresent()) {
            return new ResponseEntity<>(userTransportData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create user response entity.
     *
     * @param transport the transport
     * @return the response entity
     */
    @PostMapping("/")
    @Operation(summary = "Create a userTransport",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "500", description = "Bad request")
            })
    public ResponseEntity<Transport> createUser(@RequestBody final
                                                Transport transport) {
        try {
            Transport resTransport = transportRepository.save(new
                    Transport(transport));
            logger.info("creating Transport...");
            return new ResponseEntity<>(resTransport, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

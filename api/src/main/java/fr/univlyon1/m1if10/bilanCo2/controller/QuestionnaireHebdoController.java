package fr.univlyon1.m1if10.bilanCo2.controller;

import fr.univlyon1.m1if10.bilanCo2.model.QuestionnaireHebdo;
import fr.univlyon1.m1if10.bilanCo2.repository.QuestionnaireHebdoRepository;
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
 * The type Questionnaire hebdo controller.
 */
//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/utilisateur/questionnaireHebdo")
public class QuestionnaireHebdoController {

    private final Logger logger = Logger.getLogger(getClass().getName());

    private QuestionnaireHebdoRepository questionnaireHebdoRepository;

    /**
     * Instantiates a new Questionnaire hebdo controller.
     *
     * @param questionnaireHebdoRepository the questionnaire hebdo repository
     */
    @Autowired
    public QuestionnaireHebdoController(final QuestionnaireHebdoRepository
                                                    questionnaireHebdoRepository) {
        this.questionnaireHebdoRepository = questionnaireHebdoRepository;
    }

    /**
     * Gets all user questionnaire hebdo.
     *
     * @return the all user questionnaire hebdo
     */
    @GetMapping(value = "/", produces = {"application/json"})
    @Operation(summary = "Get all QuestionnaireHebdo in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    })
            })
    public ResponseEntity<List<QuestionnaireHebdo>> getAllUserQuestionnaireHebdo() {
        try {
            return new ResponseEntity<>(questionnaireHebdoRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Gets user questionnaire hebdo by id.
     *
     * @param id the id
     * @return the user questionnaire hebdo by id
     */
    @GetMapping(value = "/{id}", produces = {"application/json"})
    @Operation(summary = "Get one userQuestionnaireHebdo in json format",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Successful operation", content = {
                            @Content(mediaType = "application/json")
                    }),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request", content = @Content())
            })
    public ResponseEntity<QuestionnaireHebdo> getUserQuestionnaireHebdoById(@PathVariable("id")
                                                                                final long id) {
        Optional<QuestionnaireHebdo> userQuestionnaireHebdoData =
                questionnaireHebdoRepository.findById(id);

        if (userQuestionnaireHebdoData.isPresent()) {
            return new ResponseEntity<>(userQuestionnaireHebdoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create user response entity.
     *
     * @param questionnaireHebdo the questionnaire hebdo
     * @return the response entity
     */
    @PostMapping("/")
    @Operation(summary = "Create a userQuestionnaireHebdo",
            tags = "Operation REST",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            })
    public ResponseEntity<QuestionnaireHebdo> createUser(@RequestBody final
                                                           QuestionnaireHebdo questionnaireHebdo) {
        try {
            QuestionnaireHebdo resQuestionnaireHebdo = questionnaireHebdoRepository.save(new
                    QuestionnaireHebdo(questionnaireHebdo));
            logger.info("creating QuestionnaireHebdo...");
            return new ResponseEntity<>(resQuestionnaireHebdo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
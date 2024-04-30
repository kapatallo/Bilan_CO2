package fr.univlyon1.m1if10.bilanCo2.controller;

import fr.univlyon1.m1if10.bilanCo2.dto.UserDto;
import fr.univlyon1.m1if10.bilanCo2.model.Utilisateur;
import fr.univlyon1.m1if10.bilanCo2.repository.UtilisateurRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;

import java.util.Optional;

import static fr.univlyon1.m1if10.bilanCo2.utils.JwtHelper.verifyToken;
import static fr.univlyon1.m1if10.bilanCo2.utils.JwtHelper.generateToken;
import static fr.univlyon1.m1if10.bilanCo2.utils.JwtHelper.noLifeTimeToken;

/**
 * The type Operation controller.
 */
@RestController
/*@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000", "http://localhost",
                        "https://192.168.75.51/api", "http://192.168.75.51",
                        "https://192.168.75.51"})*/
public class OperationController {

    private final UtilisateurRepository utilisateurRepository;

    private static final String BEARER = "Bearer ";

    private static final String AUTHENTIFICATION = "Authentication";

    /**
     * Instantiates a new Utilisateur controller.
     *
     * @param utilisateurRepository the utilisateur repository
     */
    @Autowired
    public OperationController(final UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Login json response entity.
     *
     * @param userDto the user dto
     * @param origin  the origin
     * @return the response entity
     * @throws AuthenticationException the authentication exception
     * @throws Exception               the exception
     */
    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "To let a user connect",
            tags = "Operation controller",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "404", description = "Not found")
            })
    public ResponseEntity<Void> loginJson(@RequestBody final UserDto userDto,
                                          @RequestHeader("Origin") final String origin)
            throws AuthenticationException, IllegalArgumentException {
        if (userDto.getemail() == null || userDto.getmdp() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Utilisateur> user = utilisateurRepository.findByMailMdp(userDto.getemail(),
                userDto.getmdp());
        if (user.isPresent()) {
                String token = generateToken(user.get().getId(), origin);
                HttpHeaders headers = new HttpHeaders();
                headers.add(AUTHENTIFICATION, BEARER + token);
                headers.add("Access-Control-Expose-Headers", AUTHENTIFICATION);
                return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Méthode destinée pour valider l'authentification d'un utilisateur.
     *
     * @param jwt    Le token JWT qui se trouve dans le header "Authentication" de la requête
     * @param origin L'origine de la requête (pour la comparer avec celle du client,
     *               stockée dans le token JWT)
     * @return Une réponse vide avec un code de statut approprié (204, 400, 401).
     * @throws AuthenticationException the AUTHENTIFICATION exception
     * @throws Exception               the exception
     */
    @GetMapping("/authenticate")
    @Operation(summary = "To let a user authentificate",
            tags = "Operation controller",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            })
    public ResponseEntity<Long> authenticate(@RequestParam("jwt") final String jwt,
                                               @RequestParam("origin") final String origin)
        {
        String token = jwt.replace(BEARER, "");
        Long id = verifyToken(token, origin);
        Optional<Utilisateur> user = utilisateurRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get().getId(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Logout response entity.
     *
     * @param jwt    the jwt
     * @param origin the origin
     * @return the response entity
     * @throws AuthenticationException the authentication exception
     * @throws Exception               the exception
     */
    @PostMapping("/logout")
    @Operation(summary = "To let a user disconnect",
            tags = "Operation controller",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No content"),
                    @ApiResponse(responseCode = "400", description = "Bad request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            })
    public ResponseEntity<Void> logout(@RequestHeader(AUTHENTIFICATION) final String jwt,
                                       @RequestHeader("origin") final String origin)
            throws AuthenticationException, IllegalArgumentException {
        String token = jwt.replace(BEARER, "");
        long id = verifyToken(token, origin);
        Optional<Utilisateur> user = utilisateurRepository.findById(id);
        if (user.isPresent()) {
            String newToken = noLifeTimeToken(id, origin);
            HttpHeaders headers = new HttpHeaders();
            headers.add(AUTHENTIFICATION, BEARER + newToken);
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}

package fr.univlyon1.m1if10.bilanCo2.controller;

import fr.univlyon1.m1if10.bilanCo2.model.Utilisateur;
import fr.univlyon1.m1if10.bilanCo2.repository.UtilisateurRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.AuthenticationException;
import java.util.Optional;
import java.util.logging.Logger;

import static fr.univlyon1.m1if10.bilanCo2.utils.JwtHelper.verifyToken;

/**
 * The type Operation controller.
 */
@RestController
public class OperationController {

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
    public OperationController(final UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    /**
     * Méthode destinée pour valider l'authentification d'un utilisateur.
     *
     * @param jwt    Le token JWT qui se trouve dans le header "Authentication" de la requête
     * @param origin L'origine de la requête (pour la comparer avec celle du client,
     *               stockée dans le token JWT)
     * @return Une réponse vide avec un code de statut approprié (204, 400, 401).
     * @throws AuthenticationException the authentication exception
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
            throws AuthenticationException, Exception {
        String token = jwt.replace("Bearer ", "");
        Long id = verifyToken(token, origin);
        Optional<Utilisateur> user = utilisateurRepository.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get().getId(), HttpStatus.OK);
        } else {
            throw new AuthenticationException("le token à expiré");
        }
    }
}

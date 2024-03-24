package fr.univlyon1.m1if10.bilanCo2.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

/**
 * Classe qui centralise les opérations de validation et de génération d'un token "métier", c'est-à-dire dédié à cette application.
 *
 * @author Lionel Médini
 */
public class JwtHelper {
    private static final String SECRET = "monsecret2024";
    private static final String ISSUER = "M1IF10 2024";
    private static final long LIFETIME = 1800000; // Durée de vie d'un token : 30 minutes ; vous pouvez le modifier pour tester
    private static final Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    /**
     * Pour valider la règle <code>HideUtilityClassConstructorCheck</code> de CheckStyle...
     * Une classe utilitaire qui n'a que des méthodes statiques ne doit pas avoir de constructeur public, parce qu'il ne servirait à rien de l'instancier.
     */
    private JwtHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * Vérifie l'authentification d'un utilisateur grâce à un token JWT.
     *
     * @param token le token à vérifier
     * @param req   la requête HTTP (nécessaire pour vérifier si l'origine de la requête est la même que celle du token
     * @return un booléen qui indique si le token est bien formé et valide (pas expiré) et si l'utilisateur est authentifié
     */
    public static String verifyToken(String token, @NotNull HttpServletRequest req) throws NullPointerException, JWTVerificationException {
        JWTVerifier authenticationVerifier = JWT.require(ALGORITHM)
                .withIssuer(ISSUER)
                .build();

        authenticationVerifier.verify(token); // Lève une NullPointerException si le token n'existe pas, et une JWTVerificationException s'il est invalide
        DecodedJWT jwt = JWT.decode(token); // Pourrait lever une JWTDecodeException mais comme le token est vérifié avant, cela ne devrait pas arriver
        return jwt.getClaim("sub").asString();
    }

    /**
     * Vérifie dans le token si un user est affecté à un todo_.
     *
     * @param token  le token à vérifier
     * @param todoId l'id du todo_ dont on veut savoir si l'utilisateur lui est assigné
     * @return un booléen indiquant si le token contient un booléen admin à true
     */
    public static boolean isAssigned(String token, int todoId) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITHM).withClaimPresence("member").build();
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("assignedTo").asList(Integer.class).contains(todoId);
        } catch (JWTVerificationException e) {
            return false;
        }
    }

    /**
     * Crée un token avec les caractéristiques de l'utilisateur.
     *
     * @param subject    le login de l'utilisateur
     * @param assignedTo la liste des todos assignés à l'utilisateur
     * @param req        la requête HTTP pour pouvoir en extraire l'origine avec getOrigin()
     * @return le token signé
     * @throws JWTCreationException si les paramètres ne permettent pas de créer un token
     */
    public static String generateToken(String subject, List<Integer> assignedTo, HttpServletRequest req) throws JWTCreationException {
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(subject)
                .withClaim("assignedTo", assignedTo)
                .withExpiresAt(new Date(new Date().getTime() + LIFETIME))
                .sign(ALGORITHM);
    }
}
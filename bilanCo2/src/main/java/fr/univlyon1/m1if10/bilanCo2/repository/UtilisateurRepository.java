package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The interface Utilisateur repository.
 */
@Component
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

    @Query("SELECT U FROM Utilisateur U WHERE U.email=:email AND U.mdp=:mdp")
    Optional<Utilisateur> findByMailMdp(@Param("email") String email, @Param("mdp") String mdp);
}

package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.UtilisateurInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * The interface Utilisateur infos repository.
 */
@Component
public interface UtilisateurInfosRepository  extends JpaRepository<UtilisateurInfos, Long> {
}

package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.UtilisateurInfos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * The interface Utilisateur infos repository.
 */
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000", "http://localhost",
                        "https://192.168.75.51/api", "http://192.168.75.51",
                        "https://192.168.75.51"})
@Component
public interface UtilisateurInfosRepository  extends JpaRepository<UtilisateurInfos, Long> {
}

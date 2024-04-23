
package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * The interface Alimentation repository.
 */
@Component
public interface AlimentationRepository  extends JpaRepository<Utilisateur, Long> {
}

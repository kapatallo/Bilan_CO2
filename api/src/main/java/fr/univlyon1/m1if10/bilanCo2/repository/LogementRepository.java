package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * The interface Logement repository.
 */
@Component
public interface LogementRepository extends JpaRepository<Logement, Long> {
}

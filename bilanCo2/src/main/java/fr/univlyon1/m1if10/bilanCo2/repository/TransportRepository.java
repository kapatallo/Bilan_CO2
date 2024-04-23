package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * The interface Transport repository.
 */
@Component
public interface TransportRepository  extends JpaRepository<Utilisateur, Long> {
}

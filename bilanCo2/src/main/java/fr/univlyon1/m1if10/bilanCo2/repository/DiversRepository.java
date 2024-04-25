package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Divers;
import fr.univlyon1.m1if10.bilanCo2.model.QuestionnaireHebdo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The interface Divers repository.
 */
@Component
public interface DiversRepository extends JpaRepository<Divers, Long> {

    @Query("SELECT DR FROM QuestionnaireHebdo DR WHERE DR.annee=:annee AND DR.semaine=:semaine AND DR.id=:id")
    Optional<Divers> findByIdent(@Param("annee") int annee, @Param("semaine") int semaine, @Param("id") int id);
}

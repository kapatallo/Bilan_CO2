package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Logement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The interface Logement repository.
 */
@Component
public interface LogementRepository extends JpaRepository<Logement, Long> {

    /**
     * Find by ident optional.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     * @return the optional
     */
    @Query("SELECT LR FROM QuestionnaireHebdo LR WHERE LR.annee=:annee AND LR.semaine=:semaine"
            + " AND LR.id=:id")
    Optional<Logement> findByIdent(@Param("annee") int annee, @Param("semaine") int semaine,
                                   @Param("id") int id);
}

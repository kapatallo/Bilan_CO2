
package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Alimentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The interface Alimentation repository.
 */
@Component
public interface AlimentationRepository  extends JpaRepository<Alimentation, Long> {

    /**
     * Find by ident optional.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     * @return the optional
     */
    @Query("SELECT AR FROM QuestionnaireHebdo AR WHERE AR.annee=:annee AND AR.semaine=:semaine "
            + "AND AR.id=:id")
    Optional<Alimentation> findByIdent(@Param("annee") int annee, @Param("semaine") int semaine,
                                       @Param("id") int id);
}

package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.QuestionnaireHebdo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The interface Questionnaire hebdo repository.
@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:3000", "http://localhost",
                        "https://192.168.75.51/api", "http://192.168.75.51",
                        "https://192.168.75.51"})*/
@Component
public interface QuestionnaireHebdoRepository  extends JpaRepository<QuestionnaireHebdo, Long> {
    /**
     * Find by ident optional.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     * @return the optional
     */
    @Query("SELECT QH FROM QuestionnaireHebdo QH WHERE QH.annee=:annee AND QH.semaine=:semaine"
            + " AND QH.id=:id")
    Optional<QuestionnaireHebdo> findByIdent(@Param("annee") int annee, @Param("semaine")
    int semaine, @Param("id") int id);

}

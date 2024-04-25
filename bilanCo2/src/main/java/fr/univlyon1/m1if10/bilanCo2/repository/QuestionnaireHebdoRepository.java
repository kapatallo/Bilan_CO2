package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Ident;
import fr.univlyon1.m1if10.bilanCo2.model.QuestionnaireHebdo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The interface Questionnaire hebdo repository.
 */
@Component
public interface QuestionnaireHebdoRepository  extends JpaRepository<QuestionnaireHebdo, Long> {
    @Query("SELECT QH FROM QuestionnaireHebdo QH WHERE QH.annee=:annee AND QH.semaine=:semaine AND QH.id=:id")
    Optional<QuestionnaireHebdo> findByIdent(@Param("annee") int annee, @Param("semaine") int semaine, @Param("id") int id);

}

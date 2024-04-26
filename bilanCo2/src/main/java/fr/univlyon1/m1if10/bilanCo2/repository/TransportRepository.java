package fr.univlyon1.m1if10.bilanCo2.repository;

import fr.univlyon1.m1if10.bilanCo2.model.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The interface Transport repository.
 */
@Component
public interface TransportRepository  extends JpaRepository<Transport, Long> {

    /**
     * Find by ident optional.
     *
     * @param annee   the annee
     * @param semaine the semaine
     * @param id      the id
     * @return the optional
     */
    @Query("SELECT TR FROM Transport TR WHERE TR.annee=:annee AND TR.semaine=:semaine "
            + "AND TR.id=:id")
    Optional<Transport> findByIdent(@Param("annee") int annee, @Param("semaine") int semaine,
                                    @Param("id") int id);
}

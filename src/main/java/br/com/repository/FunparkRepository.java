package br.com.repository;
import br.com.domain.Funpark;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Funpark entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FunparkRepository extends JpaRepository<Funpark, Long> {

}

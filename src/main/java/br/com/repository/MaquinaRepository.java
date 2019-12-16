package br.com.repository;
import br.com.domain.Maquina;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Maquina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {

}

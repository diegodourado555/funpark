package br.com.repository;
import br.com.domain.ContaCorrente;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContaCorrente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {

}

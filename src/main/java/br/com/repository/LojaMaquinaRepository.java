package br.com.repository;

import br.com.domain.LojaMaquina;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LojaMaquina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LojaMaquinaRepository extends JpaRepository<LojaMaquina, Long> {

}

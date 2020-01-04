package br.com.repository;

import br.com.domain.HistoricoMaquina;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HistoricoMaquina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoricoMaquinaRepository extends JpaRepository<HistoricoMaquina, Long> {

}

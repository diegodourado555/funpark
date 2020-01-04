package br.com.repository;

import br.com.domain.HistoricoOperadorCaixa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the HistoricoOperadorCaixa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface HistoricoOperadorCaixaRepository extends JpaRepository<HistoricoOperadorCaixa, Long> {

}

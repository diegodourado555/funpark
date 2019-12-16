package br.com.repository;
import br.com.domain.OperadorCaixa;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OperadorCaixa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperadorCaixaRepository extends JpaRepository<OperadorCaixa, Long> {

}

package br.com.repository;

import br.com.domain.Despesas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Despesas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DespesasRepository extends JpaRepository<Despesas, Long> {

}

package br.com.repository;

import br.com.domain.GrupoMaquina;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the GrupoMaquina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GrupoMaquinaRepository extends JpaRepository<GrupoMaquina, Long> {

}

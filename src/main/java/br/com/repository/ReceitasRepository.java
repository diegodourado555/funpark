package br.com.repository;
import br.com.domain.Receitas;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Receitas entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReceitasRepository extends JpaRepository<Receitas, Long> {

}

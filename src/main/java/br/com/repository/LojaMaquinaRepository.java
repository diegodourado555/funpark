package br.com.repository;

import br.com.domain.LojaMaquina;
import br.com.service.dto.LojaMaquinaDTO;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LojaMaquina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LojaMaquinaRepository extends JpaRepository<LojaMaquina, Long> {
	
	@Query(value = "SELECT new br.com.service.dto.LojaMaquinaDTO(lm.id, m.id, m.nome, l.id, l.nomeFantasia) "
			+ "FROM LojaMaquina lm "
			+ "LEFT OUTER JOIN Maquina m ON m.id = lm.maquina.id "
			+ "LEFT OUTER JOIN Loja l ON l.id = lm.loja.id")
	List<LojaMaquinaDTO> findAllWithMachineAndStoreDescription();
}

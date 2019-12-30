package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.domain.Maquina;
import br.com.service.dto.MaquinaDTO;


/**
 * Spring Data  repository for the Maquina entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MaquinaRepository extends JpaRepository<Maquina, Long> {
	
	@Query(value = "SELECT new br.com.service.dto.MaquinaDTO(m.id, m.nome, gm.id, gm.nome) "
			+ "FROM Maquina m "
			+ "LEFT OUTER JOIN GrupoMaquina gm ON gm.id = m.grupoMaquina.id")
	List<MaquinaDTO> findAllWithGroupDescription();

}

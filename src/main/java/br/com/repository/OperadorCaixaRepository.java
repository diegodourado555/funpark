package br.com.repository;

import br.com.domain.OperadorCaixa;
import br.com.service.dto.OperadorCaixaDTO;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OperadorCaixa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OperadorCaixaRepository extends JpaRepository<OperadorCaixa, Long> {
	
	@Query(value = "SELECT new br.com.service.dto.OperadorCaixaDTO(o.id, o.nome, o.cpf, l.id, l.nomeFantasia) FROM OperadorCaixa o JOIN Loja l ON l.id = o.loja.id")
	List<OperadorCaixaDTO> findAllWithStoreDescription();
}

package br.com.repository;

import br.com.domain.ContaCorrente;
import br.com.service.dto.ContaCorrenteDTO;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ContaCorrente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {
	
	@Query(value = "SELECT new br.com.service.dto.ContaCorrenteDTO(cc.id, cc.valor, cc.data, cc.metodoPagamento, r.id, r.descricao, d.id, d.descricao, op.id, op.nome, l.id, l.nomeFantasia) FROM ContaCorrente cc JOIN Receitas r ON r.id = cc.receita.id  JOIN Despesas d ON d.id = cc.despesa.id  JOIN OperadorCaixa op ON op.id = cc.operadorCaixa.id JOIN Loja l ON l.id = cc.loja.id")
	List<ContaCorrenteDTO> findAllWithDescription();
}

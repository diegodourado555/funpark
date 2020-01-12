package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.domain.ContaCorrente;
import br.com.service.dto.ContaCorrenteDTO;


/**
 * Spring Data  repository for the ContaCorrente entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Long> {
	
	@Query(value = "SELECT cc "
			+ "FROM ContaCorrente cc "
			+ "LEFT OUTER JOIN Receitas r ON r.id = cc.receita.id  "
			+ "LEFT OUTER JOIN Despesas d ON d.id = cc.despesa.id  "
			+ "LEFT OUTER JOIN OperadorCaixa op ON op.id = cc.operadorCaixa.id "
			+ "LEFT OUTER JOIN Loja l ON l.id = cc.loja.id "
			+ "WHERE LOWER(cc.descricao) like LOWER(CONCAT('%', :descricao,'%'))")
	List<ContaCorrente> search(@Param("descricao") String descricao);

}

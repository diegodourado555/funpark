package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.ContaCorrenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link ContaCorrente} and its DTO {@link ContaCorrenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {ReceitasMapper.class, DespesasMapper.class, OperadorCaixaMapper.class, LojaMapper.class})
public interface ContaCorrenteMapper extends EntityMapper<ContaCorrenteDTO, ContaCorrente> {

    @Mapping(source = "receita.id", target = "receitaId")
    @Mapping(source = "receita.descricao", target = "receitaDescricao")
    @Mapping(source = "despesa.id", target = "despesaId")
    @Mapping(source = "despesa.descricao", target = "despesaDescricao")
    @Mapping(source = "operadorCaixa.id", target = "operadorCaixaId")
    @Mapping(source = "operadorCaixa.nome", target = "operadorCaixaNome")
    @Mapping(source = "loja.id", target = "lojaId")
    @Mapping(source = "loja.nomeFantasia", target = "lojaNomeFantasia")
    ContaCorrenteDTO toDto(ContaCorrente contaCorrente);

    @Mapping(source = "receitaId", target = "receita")
    @Mapping(source = "despesaId", target = "despesa")
    @Mapping(source = "operadorCaixaId", target = "operadorCaixa")
    @Mapping(source = "lojaId", target = "loja")
    ContaCorrente toEntity(ContaCorrenteDTO contaCorrenteDTO);

    default ContaCorrente fromId(Long id) {
        if (id == null) {
            return null;
        }
        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.setId(id);
        return contaCorrente;
    }
}

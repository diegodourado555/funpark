package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.OperadorCaixaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperadorCaixa} and its DTO {@link OperadorCaixaDTO}.
 */
@Mapper(componentModel = "spring", uses = {LojaMapper.class})
public interface OperadorCaixaMapper extends EntityMapper<OperadorCaixaDTO, OperadorCaixa> {

    @Mapping(source = "loja.id", target = "lojaId")
    OperadorCaixaDTO toDto(OperadorCaixa operadorCaixa);

    @Mapping(source = "lojaId", target = "loja")
    OperadorCaixa toEntity(OperadorCaixaDTO operadorCaixaDTO);

    default OperadorCaixa fromId(Long id) {
        if (id == null) {
            return null;
        }
        OperadorCaixa operadorCaixa = new OperadorCaixa();
        operadorCaixa.setId(id);
        return operadorCaixa;
    }
}

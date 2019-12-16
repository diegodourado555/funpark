package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.DespesasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Despesas} and its DTO {@link DespesasDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DespesasMapper extends EntityMapper<DespesasDTO, Despesas> {



    default Despesas fromId(Long id) {
        if (id == null) {
            return null;
        }
        Despesas despesas = new Despesas();
        despesas.setId(id);
        return despesas;
    }
}

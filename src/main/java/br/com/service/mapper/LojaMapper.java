package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.LojaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Loja} and its DTO {@link LojaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LojaMapper extends EntityMapper<LojaDTO, Loja> {



    default Loja fromId(Long id) {
        if (id == null) {
            return null;
        }
        Loja loja = new Loja();
        loja.setId(id);
        return loja;
    }
}

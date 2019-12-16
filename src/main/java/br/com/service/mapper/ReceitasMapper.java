package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.ReceitasDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Receitas} and its DTO {@link ReceitasDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ReceitasMapper extends EntityMapper<ReceitasDTO, Receitas> {



    default Receitas fromId(Long id) {
        if (id == null) {
            return null;
        }
        Receitas receitas = new Receitas();
        receitas.setId(id);
        return receitas;
    }
}

package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.FunparkDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Funpark} and its DTO {@link FunparkDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FunparkMapper extends EntityMapper<FunparkDTO, Funpark> {



    default Funpark fromId(Long id) {
        if (id == null) {
            return null;
        }
        Funpark funpark = new Funpark();
        funpark.setId(id);
        return funpark;
    }
}

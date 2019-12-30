package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.MaquinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Maquina} and its DTO {@link MaquinaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MaquinaMapper extends EntityMapper<MaquinaDTO, Maquina> {



    default Maquina fromId(Long id) {
        if (id == null) {
            return null;
        }
        Maquina maquina = new Maquina();
        maquina.setId(id);
        return maquina;
    }
}

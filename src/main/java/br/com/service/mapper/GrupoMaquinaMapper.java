package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.GrupoMaquinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GrupoMaquina} and its DTO {@link GrupoMaquinaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface GrupoMaquinaMapper extends EntityMapper<GrupoMaquinaDTO, GrupoMaquina> {



    default GrupoMaquina fromId(Long id) {
        if (id == null) {
            return null;
        }
        GrupoMaquina grupoMaquina = new GrupoMaquina();
        grupoMaquina.setId(id);
        return grupoMaquina;
    }
}

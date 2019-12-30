package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.GrupoMaquinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link GrupoMaquina} and its DTO {@link GrupoMaquinaDTO}.
 */
@Mapper(componentModel = "spring", uses = {MaquinaMapper.class})
public interface GrupoMaquinaMapper extends EntityMapper<GrupoMaquinaDTO, GrupoMaquina> {

    @Mapping(source = "maquina.id", target = "maquinaId")
    GrupoMaquinaDTO toDto(GrupoMaquina grupoMaquina);

    @Mapping(source = "maquinaId", target = "maquina")
    GrupoMaquina toEntity(GrupoMaquinaDTO grupoMaquinaDTO);

    default GrupoMaquina fromId(Long id) {
        if (id == null) {
            return null;
        }
        GrupoMaquina grupoMaquina = new GrupoMaquina();
        grupoMaquina.setId(id);
        return grupoMaquina;
    }
}

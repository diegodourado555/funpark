package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.MaquinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Maquina} and its DTO {@link MaquinaDTO}.
 */
@Mapper(componentModel = "spring", uses = {GrupoMaquinaMapper.class})
public interface MaquinaMapper extends EntityMapper<MaquinaDTO, Maquina> {

    @Mapping(source = "grupoMaquina.id", target = "grupoMaquinaId")
    MaquinaDTO toDto(Maquina maquina);

    @Mapping(source = "grupoMaquinaId", target = "grupoMaquina")
    Maquina toEntity(MaquinaDTO maquinaDTO);

    default Maquina fromId(Long id) {
        if (id == null) {
            return null;
        }
        Maquina maquina = new Maquina();
        maquina.setId(id);
        return maquina;
    }
}

package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.HistoricoMaquinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistoricoMaquina} and its DTO {@link HistoricoMaquinaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HistoricoMaquinaMapper extends EntityMapper<HistoricoMaquinaDTO, HistoricoMaquina> {



    default HistoricoMaquina fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistoricoMaquina historicoMaquina = new HistoricoMaquina();
        historicoMaquina.setId(id);
        return historicoMaquina;
    }
}

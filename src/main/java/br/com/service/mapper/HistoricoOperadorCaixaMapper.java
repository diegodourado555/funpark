package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.HistoricoOperadorCaixaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistoricoOperadorCaixa} and its DTO {@link HistoricoOperadorCaixaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface HistoricoOperadorCaixaMapper extends EntityMapper<HistoricoOperadorCaixaDTO, HistoricoOperadorCaixa> {



    default HistoricoOperadorCaixa fromId(Long id) {
        if (id == null) {
            return null;
        }
        HistoricoOperadorCaixa historicoOperadorCaixa = new HistoricoOperadorCaixa();
        historicoOperadorCaixa.setId(id);
        return historicoOperadorCaixa;
    }
}

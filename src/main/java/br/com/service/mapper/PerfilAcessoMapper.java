package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.PerfilAcessoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PerfilAcesso} and its DTO {@link PerfilAcessoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PerfilAcessoMapper extends EntityMapper<PerfilAcessoDTO, PerfilAcesso> {



    default PerfilAcesso fromId(Long id) {
        if (id == null) {
            return null;
        }
        PerfilAcesso perfilAcesso = new PerfilAcesso();
        perfilAcesso.setId(id);
        return perfilAcesso;
    }
}

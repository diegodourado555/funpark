package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.LojaMaquinaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LojaMaquina} and its DTO {@link LojaMaquinaDTO}.
 */
@Mapper(componentModel = "spring", uses = {MaquinaMapper.class, LojaMapper.class})
public interface LojaMaquinaMapper extends EntityMapper<LojaMaquinaDTO, LojaMaquina> {

    @Mapping(source = "maquina.id", target = "maquinaId")
    @Mapping(source = "maquina.nome", target = "maquinaNome")
    @Mapping(source = "loja.id", target = "lojaId")
    @Mapping(source = "loja.nomeFantasia", target = "lojaNomeFantasia")
    LojaMaquinaDTO toDto(LojaMaquina lojaMaquina);

    @Mapping(source = "maquinaId", target = "maquina.id")
    @Mapping(source = "maquinaNome", target = "maquina.nome")
    @Mapping(source = "lojaId", target = "loja.id")
    @Mapping(source = "lojaNomeFantasia", target = "loja.nomeFantasia")
    LojaMaquina toEntity(LojaMaquinaDTO lojaMaquinaDTO);

    default LojaMaquina fromId(Long id) {
        if (id == null) {
            return null;
        }
        LojaMaquina lojaMaquina = new LojaMaquina();
        lojaMaquina.setId(id);
        return lojaMaquina;
    }
}

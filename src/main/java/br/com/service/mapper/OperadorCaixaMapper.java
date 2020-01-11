package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.OperadorCaixaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link OperadorCaixa} and its DTO {@link OperadorCaixaDTO}.
 */
@Mapper(componentModel = "spring", uses = {LojaMapper.class})
public interface OperadorCaixaMapper extends EntityMapper<OperadorCaixaDTO, OperadorCaixa> {

    @Mapping(source = "loja.id", target = "lojaId")
    @Mapping(source = "loja.nomeFantasia", target = "lojaNomeFantasia")
    OperadorCaixaDTO toDto(OperadorCaixa operadorCaixa);

    @Mapping(source = "lojaId", target = "loja.id")
    @Mapping(source = "lojaNomeFantasia", target = "loja.nomeFantasia")
    OperadorCaixa toEntity(OperadorCaixaDTO operadorCaixaDTO);
    
    @BeforeMapping
    default void beforeToEntity(OperadorCaixaDTO operadorCaixaDTO, @MappingTarget OperadorCaixa operadorCaixa) {
        if(null != operadorCaixaDTO && null!=operadorCaixaDTO.getCpf() && !"".equals(operadorCaixaDTO.getCpf())) {
	    	String cpf = operadorCaixaDTO.getCpf().replace(".", "");
	        cpf = cpf.replace("-", "");
	        operadorCaixaDTO.setCpf(cpf);
        }
    }
    @AfterMapping
    default void afterToDto(OperadorCaixa operadorCaixa, @MappingTarget OperadorCaixaDTO operadorCaixaDTO) {
    	if(null != operadorCaixa && null!=operadorCaixa.getCpf() && !"".equals(operadorCaixa.getCpf())) {
    		String cpf = operadorCaixa.getCpf();
    		cpf = new StringBuilder(cpf.substring(0, 3)).append(".")
    				.append(cpf.substring(3, 6)).append(".")
    				.append(cpf.substring(6, 9)).append("-")
    				.append(cpf.substring(9, 11)).toString();
    		operadorCaixaDTO.setCpf(cpf);
    	}
    }
    
    default OperadorCaixa fromId(Long id) {
        if (id == null) {
            return null;
        }
        OperadorCaixa operadorCaixa = new OperadorCaixa();
        operadorCaixa.setId(id);
        return operadorCaixa;
    }
}

package br.com.service.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import br.com.domain.Loja;
import br.com.service.dto.LojaDTO;

/**
 * Mapper for the entity {@link Loja} and its DTO {@link LojaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LojaMapper extends EntityMapper<LojaDTO, Loja> {
	
	Loja toEntity(LojaDTO lojaDTO);
	
	LojaDTO toDto(Loja loja);
	
	@BeforeMapping
    default void beforeToEntity(LojaDTO lojaDTO, @MappingTarget Loja loja) {
        if(null != lojaDTO && null!=lojaDTO.getcNPJ() && !"".equals(lojaDTO.getcNPJ())) {
	    	String cnpj = lojaDTO.getcNPJ().replace(".", "");
	        cnpj = cnpj.replace("/", "");
	        cnpj = cnpj.replace("-", "");
	        lojaDTO.setcNPJ(cnpj);
        }
    }
	
	@AfterMapping
    default void afterToDto(Loja loja, @MappingTarget LojaDTO lojaDTO) {
    	if(null != loja && null!=loja.getcNPJ() && !"".equals(loja.getcNPJ())) {
    		String cnpj = loja.getcNPJ();
    		cnpj = new StringBuilder(cnpj.substring(0, 2)).append(".")
    				.append(cnpj.substring(2, 5)).append(".")
    				.append(cnpj.substring(5, 8)).append("/")
    				.append(cnpj.substring(8, 12)).append("-")
    				.append(cnpj.substring(12, 14)).toString();
    		lojaDTO.setcNPJ(cnpj);
    	}
    }
	
    default Loja fromId(Long id) {
        if (id == null) {
            return null;
        }
        Loja loja = new Loja();
        loja.setId(id);
        return loja;
    }
}

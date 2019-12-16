package br.com.service.mapper;

import br.com.domain.*;
import br.com.service.dto.MenuPerfilDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MenuPerfil} and its DTO {@link MenuPerfilDTO}.
 */
@Mapper(componentModel = "spring", uses = {MenuMapper.class, PerfilAcessoMapper.class})
public interface MenuPerfilMapper extends EntityMapper<MenuPerfilDTO, MenuPerfil> {

    @Mapping(source = "menu.id", target = "menuId")
    @Mapping(source = "perfil.id", target = "perfilId")
    MenuPerfilDTO toDto(MenuPerfil menuPerfil);

    @Mapping(source = "menuId", target = "menu")
    @Mapping(source = "perfilId", target = "perfil")
    MenuPerfil toEntity(MenuPerfilDTO menuPerfilDTO);

    default MenuPerfil fromId(Long id) {
        if (id == null) {
            return null;
        }
        MenuPerfil menuPerfil = new MenuPerfil();
        menuPerfil.setId(id);
        return menuPerfil;
    }
}

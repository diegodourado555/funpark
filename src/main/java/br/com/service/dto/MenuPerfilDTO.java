package br.com.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.domain.MenuPerfil} entity.
 */
public class MenuPerfilDTO implements Serializable {

    private Long id;

    private Long idMenu;

    private Long idPerfil;


    private Long menuId;

    private Long perfilId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilAcessoId) {
        this.perfilId = perfilAcessoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuPerfilDTO menuPerfilDTO = (MenuPerfilDTO) o;
        if (menuPerfilDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuPerfilDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuPerfilDTO{" +
            "id=" + getId() +
            ", idMenu=" + getIdMenu() +
            ", idPerfil=" + getIdPerfil() +
            ", menu=" + getMenuId() +
            ", perfil=" + getPerfilId() +
            "}";
    }
}

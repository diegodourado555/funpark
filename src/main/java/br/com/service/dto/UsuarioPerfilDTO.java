package br.com.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.domain.UsuarioPerfil} entity.
 */
public class UsuarioPerfilDTO implements Serializable {

    private Long id;

    private Long idUsuario;

    private Long idPerfil;


    private Long usuarioId;

    private Long perfilId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
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

        UsuarioPerfilDTO usuarioPerfilDTO = (UsuarioPerfilDTO) o;
        if (usuarioPerfilDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), usuarioPerfilDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UsuarioPerfilDTO{" +
            "id=" + getId() +
            ", idUsuario=" + getIdUsuario() +
            ", idPerfil=" + getIdPerfil() +
            ", usuario=" + getUsuarioId() +
            ", perfil=" + getPerfilId() +
            "}";
    }
}

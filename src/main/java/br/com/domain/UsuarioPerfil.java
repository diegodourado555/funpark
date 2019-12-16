package br.com.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A UsuarioPerfil.
 */
@Entity
@Table(name = "usuario_perfil")
public class UsuarioPerfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "id_perfil")
    private Long idPerfil;

    @OneToOne
    @JoinColumn(unique = true)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(unique = true)
    private PerfilAcesso perfil;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public UsuarioPerfil idUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public UsuarioPerfil idPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
        return this;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public UsuarioPerfil usuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PerfilAcesso getPerfil() {
        return perfil;
    }

    public UsuarioPerfil perfil(PerfilAcesso perfilAcesso) {
        this.perfil = perfilAcesso;
        return this;
    }

    public void setPerfil(PerfilAcesso perfilAcesso) {
        this.perfil = perfilAcesso;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UsuarioPerfil)) {
            return false;
        }
        return id != null && id.equals(((UsuarioPerfil) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "UsuarioPerfil{" +
            "id=" + getId() +
            ", idUsuario=" + getIdUsuario() +
            ", idPerfil=" + getIdPerfil() +
            "}";
    }
}

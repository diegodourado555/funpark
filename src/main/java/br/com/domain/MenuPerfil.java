package br.com.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A MenuPerfil.
 */
@Entity
@Table(name = "menu_perfil")
public class MenuPerfil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_menu")
    private Long idMenu;

    @Column(name = "id_perfil")
    private Long idPerfil;

    @OneToOne
    @JoinColumn(unique = true)
    private Menu menu;

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

    public Long getIdMenu() {
        return idMenu;
    }

    public MenuPerfil idMenu(Long idMenu) {
        this.idMenu = idMenu;
        return this;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public Long getIdPerfil() {
        return idPerfil;
    }

    public MenuPerfil idPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
        return this;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Menu getMenu() {
        return menu;
    }

    public MenuPerfil menu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public PerfilAcesso getPerfil() {
        return perfil;
    }

    public MenuPerfil perfil(PerfilAcesso perfilAcesso) {
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
        if (!(o instanceof MenuPerfil)) {
            return false;
        }
        return id != null && id.equals(((MenuPerfil) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MenuPerfil{" +
            "id=" + getId() +
            ", idMenu=" + getIdMenu() +
            ", idPerfil=" + getIdPerfil() +
            "}";
    }
}

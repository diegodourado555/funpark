package br.com.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Maquina.
 */
@Entity
@Table(name = "maquina")
public class Maquina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "id_grupo_maquina")
    private Long idGrupoMaquina;

    @OneToOne
    @JoinColumn(unique = true)
    private GrupoMaquina grupoMaquina;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Maquina nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdGrupoMaquina() {
        return idGrupoMaquina;
    }

    public Maquina idGrupoMaquina(Long idGrupoMaquina) {
        this.idGrupoMaquina = idGrupoMaquina;
        return this;
    }

    public void setIdGrupoMaquina(Long idGrupoMaquina) {
        this.idGrupoMaquina = idGrupoMaquina;
    }

    public GrupoMaquina getGrupoMaquina() {
        return grupoMaquina;
    }

    public Maquina grupoMaquina(GrupoMaquina grupoMaquina) {
        this.grupoMaquina = grupoMaquina;
        return this;
    }

    public void setGrupoMaquina(GrupoMaquina grupoMaquina) {
        this.grupoMaquina = grupoMaquina;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Maquina)) {
            return false;
        }
        return id != null && id.equals(((Maquina) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Maquina{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", idGrupoMaquina=" + getIdGrupoMaquina() +
            "}";
    }
}

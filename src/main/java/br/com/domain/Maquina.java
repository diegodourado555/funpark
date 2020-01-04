package br.com.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

import br.com.domain.enumeration.SituacaoMaquina;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private SituacaoMaquina situacao;

    @ManyToOne
    @JsonIgnoreProperties("maquinas")
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

    public SituacaoMaquina getSituacao() {
        return situacao;
    }

    public Maquina situacao(SituacaoMaquina situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(SituacaoMaquina situacao) {
        this.situacao = situacao;
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
            ", situacao='" + getSituacao() + "'" +
            "}";
    }
}

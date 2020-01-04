package br.com.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.domain.enumeration.SituacaoMaquina;

/**
 * A HistoricoMaquina.
 */
@Entity
@Table(name = "historico_maquina")
public class HistoricoMaquina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "data")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private SituacaoMaquina situacao;

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

    public HistoricoMaquina nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public HistoricoMaquina data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public SituacaoMaquina getSituacao() {
        return situacao;
    }

    public HistoricoMaquina situacao(SituacaoMaquina situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(SituacaoMaquina situacao) {
        this.situacao = situacao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoricoMaquina)) {
            return false;
        }
        return id != null && id.equals(((HistoricoMaquina) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "HistoricoMaquina{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", data='" + getData() + "'" +
            ", situacao='" + getSituacao() + "'" +
            "}";
    }
}

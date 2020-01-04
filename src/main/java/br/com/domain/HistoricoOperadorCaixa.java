package br.com.domain;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.domain.enumeration.SituacaoOperadorCaixa;

/**
 * A HistoricoOperadorCaixa.
 */
@Entity
@Table(name = "historico_operador_caixa")
public class HistoricoOperadorCaixa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private Float cpf;

    @Column(name = "data")
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    @Column(name = "situacao")
    private SituacaoOperadorCaixa situacao;

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

    public HistoricoOperadorCaixa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getCpf() {
        return cpf;
    }

    public HistoricoOperadorCaixa cpf(Float cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(Float cpf) {
        this.cpf = cpf;
    }

    public LocalDate getData() {
        return data;
    }

    public HistoricoOperadorCaixa data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public SituacaoOperadorCaixa getSituacao() {
        return situacao;
    }

    public HistoricoOperadorCaixa situacao(SituacaoOperadorCaixa situacao) {
        this.situacao = situacao;
        return this;
    }

    public void setSituacao(SituacaoOperadorCaixa situacao) {
        this.situacao = situacao;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof HistoricoOperadorCaixa)) {
            return false;
        }
        return id != null && id.equals(((HistoricoOperadorCaixa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "HistoricoOperadorCaixa{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cpf=" + getCpf() +
            ", data='" + getData() + "'" +
            ", situacao='" + getSituacao() + "'" +
            "}";
    }
}

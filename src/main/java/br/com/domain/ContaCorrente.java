package br.com.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import br.com.domain.enumeration.MetodoPagamento;

/**
 * A ContaCorrente.
 */
@Entity
@Table(name = "conta_corrente")
public class ContaCorrente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "id_receita")
    private Long idReceita;

    @Column(name = "id_despesa")
    private Long idDespesa;

    @Column(name = "id_operador")
    private Long idOperador;

    @Column(name = "id_loja")
    private Long idLoja;

    @Column(name = "valor")
    private Double valor;

    @Column(name = "data")
    private Instant data;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento")
    private MetodoPagamento metodoPagamento;

    @ManyToOne
    @JsonIgnoreProperties("contaCorrentes")
    private Receitas receita;

    @ManyToOne
    @JsonIgnoreProperties("contaCorrentes")
    private Despesas despesa;

    @ManyToOne
    @JsonIgnoreProperties("contaCorrentes")
    private OperadorCaixa operadorCaixa;

    @ManyToOne
    @JsonIgnoreProperties("contaCorrentes")
    private Loja loja;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdReceita() {
        return idReceita;
    }

    public ContaCorrente idReceita(Long idReceita) {
        this.idReceita = idReceita;
        return this;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    public Long getIdDespesa() {
        return idDespesa;
    }

    public ContaCorrente idDespesa(Long idDespesa) {
        this.idDespesa = idDespesa;
        return this;
    }

    public void setIdDespesa(Long idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Long getIdOperador() {
        return idOperador;
    }

    public ContaCorrente idOperador(Long idOperador) {
        this.idOperador = idOperador;
        return this;
    }

    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    public Long getIdLoja() {
        return idLoja;
    }

    public ContaCorrente idLoja(Long idLoja) {
        this.idLoja = idLoja;
        return this;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    public Double getValor() {
        return valor;
    }

    public ContaCorrente valor(Double valor) {
        this.valor = valor;
        return this;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Instant getData() {
        return data;
    }

    public ContaCorrente data(Instant data) {
        this.data = data;
        return this;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public ContaCorrente metodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
        return this;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public Receitas getReceita() {
        return receita;
    }

    public ContaCorrente receita(Receitas receitas) {
        this.receita = receitas;
        return this;
    }

    public void setReceita(Receitas receitas) {
        this.receita = receitas;
    }

    public Despesas getDespesa() {
        return despesa;
    }

    public ContaCorrente despesa(Despesas despesas) {
        this.despesa = despesas;
        return this;
    }

    public void setDespesa(Despesas despesas) {
        this.despesa = despesas;
    }

    public OperadorCaixa getOperadorCaixa() {
        return operadorCaixa;
    }

    public ContaCorrente operadorCaixa(OperadorCaixa operadorCaixa) {
        this.operadorCaixa = operadorCaixa;
        return this;
    }

    public void setOperadorCaixa(OperadorCaixa operadorCaixa) {
        this.operadorCaixa = operadorCaixa;
    }

    public Loja getLoja() {
        return loja;
    }

    public ContaCorrente loja(Loja loja) {
        this.loja = loja;
        return this;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContaCorrente)) {
            return false;
        }
        return id != null && id.equals(((ContaCorrente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ContaCorrente{" +
            "id=" + getId() +
            ", idReceita=" + getIdReceita() +
            ", idDespesa=" + getIdDespesa() +
            ", idOperador=" + getIdOperador() +
            ", idLoja=" + getIdLoja() +
            ", valor=" + getValor() +
            ", data='" + getData() + "'" +
            ", metodoPagamento='" + getMetodoPagamento() + "'" +
            "}";
    }
}

package br.com.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A OperadorCaixa.
 */
@Entity
@Table(name = "operador_caixa")
public class OperadorCaixa implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private Float cpf;

    @OneToOne
    @JoinColumn(unique = true)
    private Loja loja;

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

    public OperadorCaixa nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getCpf() {
        return cpf;
    }

    public OperadorCaixa cpf(Float cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(Float cpf) {
        this.cpf = cpf;
    }

    public Loja getLoja() {
        return loja;
    }

    public OperadorCaixa loja(Loja loja) {
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
        if (!(o instanceof OperadorCaixa)) {
            return false;
        }
        return id != null && id.equals(((OperadorCaixa) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "OperadorCaixa{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cpf=" + getCpf() +
            "}";
    }
}

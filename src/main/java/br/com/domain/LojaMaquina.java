package br.com.domain;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A LojaMaquina.
 */
@Entity
@Table(name = "loja_maquina")
public class LojaMaquina implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @OneToOne
    @JoinColumn
    private Maquina maquina;

    @OneToOne
    @JoinColumn
    private Loja loja;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Maquina getMaquina() {
        return maquina;
    }

    public LojaMaquina maquina(Maquina maquina) {
        this.maquina = maquina;
        return this;
    }

    public void setMaquina(Maquina maquina) {
        this.maquina = maquina;
    }

    public Loja getLoja() {
        return loja;
    }

    public LojaMaquina loja(Loja loja) {
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
        if (!(o instanceof LojaMaquina)) {
            return false;
        }
        return id != null && id.equals(((LojaMaquina) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "LojaMaquina{" +
            "id=" + getId() +
            "}";
    }
}

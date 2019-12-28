package br.com.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.domain.OperadorCaixa} entity.
 */
public class OperadorCaixaDTO implements Serializable {

    private Long id;

    private String nome;

    private Float cpf;


    private Long lojaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getCpf() {
        return cpf;
    }

    public void setCpf(Float cpf) {
        this.cpf = cpf;
    }

    public Long getLojaId() {
        return lojaId;
    }

    public void setLojaId(Long lojaId) {
        this.lojaId = lojaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OperadorCaixaDTO operadorCaixaDTO = (OperadorCaixaDTO) o;
        if (operadorCaixaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), operadorCaixaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OperadorCaixaDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cpf=" + getCpf() +
            ", lojaId=" + getLojaId() +
            "}";
    }
}

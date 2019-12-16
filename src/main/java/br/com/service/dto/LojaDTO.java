package br.com.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.domain.Loja} entity.
 */
public class LojaDTO implements Serializable {

    private Long id;

    private String nomeFantasia;

    private String razaoSocial;

    private String cNPJ;

    private String endereco;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getcNPJ() {
        return cNPJ;
    }

    public void setcNPJ(String cNPJ) {
        this.cNPJ = cNPJ;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LojaDTO lojaDTO = (LojaDTO) o;
        if (lojaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lojaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LojaDTO{" +
            "id=" + getId() +
            ", nomeFantasia='" + getNomeFantasia() + "'" +
            ", razaoSocial='" + getRazaoSocial() + "'" +
            ", cNPJ='" + getcNPJ() + "'" +
            ", endereco='" + getEndereco() + "'" +
            "}";
    }
}

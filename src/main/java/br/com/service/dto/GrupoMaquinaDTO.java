package br.com.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.domain.GrupoMaquina} entity.
 */
public class GrupoMaquinaDTO implements Serializable {

    private Long id;

    private String nome;


    private Long maquinaId;

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

    public Long getMaquinaId() {
        return maquinaId;
    }

    public void setMaquinaId(Long maquinaId) {
        this.maquinaId = maquinaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GrupoMaquinaDTO grupoMaquinaDTO = (GrupoMaquinaDTO) o;
        if (grupoMaquinaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), grupoMaquinaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GrupoMaquinaDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", maquinaId=" + getMaquinaId() +
            "}";
    }
}

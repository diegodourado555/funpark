package br.com.service.dto;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link br.com.domain.LojaMaquina} entity.
 */
public class LojaMaquinaDTO implements Serializable {

    private Long id;

    private Long idLoja;

    private Long idMaquina;


    private Long maquinaId;

    private Long lojaId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
    }

    public Long getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Long idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Long getMaquinaId() {
        return maquinaId;
    }

    public void setMaquinaId(Long maquinaId) {
        this.maquinaId = maquinaId;
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

        LojaMaquinaDTO lojaMaquinaDTO = (LojaMaquinaDTO) o;
        if (lojaMaquinaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), lojaMaquinaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "LojaMaquinaDTO{" +
            "id=" + getId() +
            ", idLoja=" + getIdLoja() +
            ", idMaquina=" + getIdMaquina() +
            ", maquina=" + getMaquinaId() +
            ", loja=" + getLojaId() +
            "}";
    }
}

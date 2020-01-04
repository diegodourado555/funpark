package br.com.service.dto;
import java.io.Serializable;
import java.util.Objects;
import br.com.domain.enumeration.SituacaoMaquina;

/**
 * A DTO for the {@link br.com.domain.Maquina} entity.
 */
public class MaquinaDTO implements Serializable {

    private Long id;

    private String nome;

    private SituacaoMaquina situacao;

    private Long grupoMaquinaId;
    
    private String grupoMaquinaNome;

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

    public SituacaoMaquina getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoMaquina situacao) {
        this.situacao = situacao;
    }

    public Long getGrupoMaquinaId() {
        return grupoMaquinaId;
    }

    public void setGrupoMaquinaId(Long grupoMaquinaId) {
        this.grupoMaquinaId = grupoMaquinaId;
    }
    
    public String getGrupoMaquinaNome() {
		return grupoMaquinaNome;
	}

	public void setGrupoMaquinaNome(String grupoMaquinaNome) {
		this.grupoMaquinaNome = grupoMaquinaNome;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaquinaDTO maquinaDTO = (MaquinaDTO) o;
        if (maquinaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), maquinaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MaquinaDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", grupoMaquinaId=" + getGrupoMaquinaId() +
            ", grupoMaquinaNome=" + getGrupoMaquinaNome() +
            "}";
    }
}

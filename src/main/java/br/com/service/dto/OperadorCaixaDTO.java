package br.com.service.dto;
import java.io.Serializable;
import java.util.Objects;
import br.com.domain.enumeration.SituacaoOperadorCaixa;

/**
 * A DTO for the {@link br.com.domain.OperadorCaixa} entity.
 */
public class OperadorCaixaDTO implements Serializable {

    private Long id;

    private String nome;

    private String cpf;

    private SituacaoOperadorCaixa situacao;

    private Long lojaId;
    
    private String lojaNomeFantasia;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public SituacaoOperadorCaixa getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoOperadorCaixa situacao) {
        this.situacao = situacao;
    }

    public Long getLojaId() {
        return lojaId;
    }

    public void setLojaId(Long lojaId) {
        this.lojaId = lojaId;
    }
    
	public String getLojaNomeFantasia() {
		return lojaNomeFantasia;
	}

	public void setLojaNomeFantasia(String lojaNomeFantasia) {
		this.lojaNomeFantasia = lojaNomeFantasia;
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
            ", situacao='" + getSituacao() + "'" +
            ", lojaId=" + getLojaId() +
            ", lojaNome=" + getLojaNomeFantasia() +
            "}";
    }
}

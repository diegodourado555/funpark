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
    
    private String lojaNome;
    
    
    
    public OperadorCaixaDTO() {
		super();
	}
    
	public OperadorCaixaDTO(Long id, String nome, Float cpf, Long lojaId, String lojaNome) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.lojaId = lojaId;
		this.lojaNome = lojaNome;
	}

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
    
    
    
    public String getLojaNome() {
		return lojaNome;
	}

	public void setLojaNome(String lojaNome) {
		this.lojaNome = lojaNome;
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
            ", lojaNome=" + getLojaNome() +
            "}";
    }
}

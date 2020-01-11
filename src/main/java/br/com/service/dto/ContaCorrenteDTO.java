package br.com.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import br.com.domain.enumeration.MetodoPagamento;
import br.com.domain.enumeration.SituacaoContaCorrente;

/**
 * A DTO for the {@link br.com.domain.ContaCorrente} entity.
 */
public class ContaCorrenteDTO implements Serializable {

    private Long id;

    private Double valor;

    private Instant data;

    private String descricao;

    private MetodoPagamento metodoPagamento;

    private SituacaoContaCorrente situacao;


    private Long receitaId;
    
    private String receitaDescricao;

    private Long despesaId;
    
    private String despesaDescricao;

    private Long operadorCaixaId;
    
    private String operadorCaixaNome;

    private Long lojaId;

    private String lojaNomeFantasia;

    public ContaCorrenteDTO() {
    	super();
    }
    
    public ContaCorrenteDTO(Long id, Double valor, Instant data, String descricao, MetodoPagamento metodoPagamento,
			SituacaoContaCorrente situacao, Long receitaId, String receitaDescricao, Long despesaId,
			String despesaDescricao, Long operadorCaixaId, String operadorCaixaNome, Long lojaId,
			String lojaNomeFantasia) {
		super();
		this.id = id;
		this.valor = valor;
		this.data = data;
		this.descricao = descricao;
		this.metodoPagamento = metodoPagamento;
		this.situacao = situacao;
		this.receitaId = receitaId;
		this.receitaDescricao = receitaDescricao;
		this.despesaId = despesaId;
		this.despesaDescricao = despesaDescricao;
		this.operadorCaixaId = operadorCaixaId;
		this.operadorCaixaNome = operadorCaixaNome;
		this.lojaId = lojaId;
		this.lojaNomeFantasia = lojaNomeFantasia;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Instant getData() {
        return data;
    }

    public void setData(Instant data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public SituacaoContaCorrente getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoContaCorrente situacao) {
        this.situacao = situacao;
    }

    public Long getReceitaId() {
        return receitaId;
    }

    public void setReceitaId(Long receitasId) {
        this.receitaId = receitasId;
    }

    public Long getDespesaId() {
        return despesaId;
    }

    public void setDespesaId(Long despesasId) {
        this.despesaId = despesasId;
    }

    public Long getOperadorCaixaId() {
        return operadorCaixaId;
    }

    public void setOperadorCaixaId(Long operadorCaixaId) {
        this.operadorCaixaId = operadorCaixaId;
    }

    public Long getLojaId() {
        return lojaId;
    }

    public void setLojaId(Long lojaId) {
        this.lojaId = lojaId;
    }
    
    public String getReceitaDescricao() {
		return receitaDescricao;
	}

	public void setReceitaDescricao(String receitaDescricao) {
		this.receitaDescricao = receitaDescricao;
	}

	public String getDespesaDescricao() {
		return despesaDescricao;
	}

	public void setDespesaDescricao(String despesaDescricao) {
		this.despesaDescricao = despesaDescricao;
	}

	public String getOperadorCaixaNome() {
		return operadorCaixaNome;
	}

	public void setOperadorCaixaNome(String operadorCaixaNome) {
		this.operadorCaixaNome = operadorCaixaNome;
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

        ContaCorrenteDTO contaCorrenteDTO = (ContaCorrenteDTO) o;
        if (contaCorrenteDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), contaCorrenteDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ContaCorrenteDTO{" +
            "id=" + getId() +
            ", valor=" + getValor() +
            ", data='" + getData() + "'" +
            ", descricao='" + getDescricao() + "'" +
            ", metodoPagamento='" + getMetodoPagamento() + "'" +
            ", situacao='" + getSituacao() + "'" +
            ", receitaId=" + getReceitaId() +
            ", receitaDescricao=" + getReceitaDescricao() +
            ", despesaId=" + getDespesaId() +
            ", despesaDescricao=" + getDespesaDescricao() +
            ", operadorCaixaId=" + getOperadorCaixaId() +
            ", operadorCaixaNome=" + getOperadorCaixaNome() +
            ", lojaId=" + getLojaId() +
            ", lojaNomeFantasia=" + getLojaNomeFantasia() +
            "}";
    }
}

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

    private Long despesaId;

    private Long operadorCaixaId;

    private Long lojaId;

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
            ", despesaId=" + getDespesaId() +
            ", operadorCaixaId=" + getOperadorCaixaId() +
            ", lojaId=" + getLojaId() +
            "}";
    }
}

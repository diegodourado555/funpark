package br.com.service.dto;
import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import br.com.domain.enumeration.MetodoPagamento;

/**
 * A DTO for the {@link br.com.domain.ContaCorrente} entity.
 */
public class ContaCorrenteDTO implements Serializable {

    private Long id;

    private Long idReceita;

    private Long idDespesa;

    private Long idOperador;

    private Long idLoja;

    private Double valor;

    private Instant data;

    private MetodoPagamento metodoPagamento;


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

    public Long getIdReceita() {
        return idReceita;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
    }

    public Long getIdDespesa() {
        return idDespesa;
    }

    public void setIdDespesa(Long idDespesa) {
        this.idDespesa = idDespesa;
    }

    public Long getIdOperador() {
        return idOperador;
    }

    public void setIdOperador(Long idOperador) {
        this.idOperador = idOperador;
    }

    public Long getIdLoja() {
        return idLoja;
    }

    public void setIdLoja(Long idLoja) {
        this.idLoja = idLoja;
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

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
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
            ", idReceita=" + getIdReceita() +
            ", idDespesa=" + getIdDespesa() +
            ", idOperador=" + getIdOperador() +
            ", idLoja=" + getIdLoja() +
            ", valor=" + getValor() +
            ", data='" + getData() + "'" +
            ", metodoPagamento='" + getMetodoPagamento() + "'" +
            ", receita=" + getReceitaId() +
            ", despesa=" + getDespesaId() +
            ", operadorCaixa=" + getOperadorCaixaId() +
            ", loja=" + getLojaId() +
            "}";
    }
}

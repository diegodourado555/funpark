package br.com.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import br.com.domain.enumeration.SituacaoOperadorCaixa;

/**
 * A DTO for the {@link br.com.domain.HistoricoOperadorCaixa} entity.
 */
public class HistoricoOperadorCaixaDTO implements Serializable {

    private Long id;

    private String nome;

    private Float cpf;

    private LocalDate data;

    private SituacaoOperadorCaixa situacao;


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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public SituacaoOperadorCaixa getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoOperadorCaixa situacao) {
        this.situacao = situacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO = (HistoricoOperadorCaixaDTO) o;
        if (historicoOperadorCaixaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historicoOperadorCaixaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistoricoOperadorCaixaDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", cpf=" + getCpf() +
            ", data='" + getData() + "'" +
            ", situacao='" + getSituacao() + "'" +
            "}";
    }
}

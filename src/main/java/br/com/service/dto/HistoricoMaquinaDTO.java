package br.com.service.dto;
import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;
import br.com.domain.enumeration.SituacaoMaquina;

/**
 * A DTO for the {@link br.com.domain.HistoricoMaquina} entity.
 */
public class HistoricoMaquinaDTO implements Serializable {

    private Long id;

    private String nome;

    private LocalDate data;

    private SituacaoMaquina situacao;


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

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public SituacaoMaquina getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoMaquina situacao) {
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

        HistoricoMaquinaDTO historicoMaquinaDTO = (HistoricoMaquinaDTO) o;
        if (historicoMaquinaDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), historicoMaquinaDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "HistoricoMaquinaDTO{" +
            "id=" + getId() +
            ", nome='" + getNome() + "'" +
            ", data='" + getData() + "'" +
            ", situacao='" + getSituacao() + "'" +
            "}";
    }
}

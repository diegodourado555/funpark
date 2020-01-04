package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class HistoricoMaquinaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricoMaquinaDTO.class);
        HistoricoMaquinaDTO historicoMaquinaDTO1 = new HistoricoMaquinaDTO();
        historicoMaquinaDTO1.setId(1L);
        HistoricoMaquinaDTO historicoMaquinaDTO2 = new HistoricoMaquinaDTO();
        assertThat(historicoMaquinaDTO1).isNotEqualTo(historicoMaquinaDTO2);
        historicoMaquinaDTO2.setId(historicoMaquinaDTO1.getId());
        assertThat(historicoMaquinaDTO1).isEqualTo(historicoMaquinaDTO2);
        historicoMaquinaDTO2.setId(2L);
        assertThat(historicoMaquinaDTO1).isNotEqualTo(historicoMaquinaDTO2);
        historicoMaquinaDTO1.setId(null);
        assertThat(historicoMaquinaDTO1).isNotEqualTo(historicoMaquinaDTO2);
    }
}

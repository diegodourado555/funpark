package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class HistoricoOperadorCaixaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricoOperadorCaixaDTO.class);
        HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO1 = new HistoricoOperadorCaixaDTO();
        historicoOperadorCaixaDTO1.setId(1L);
        HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO2 = new HistoricoOperadorCaixaDTO();
        assertThat(historicoOperadorCaixaDTO1).isNotEqualTo(historicoOperadorCaixaDTO2);
        historicoOperadorCaixaDTO2.setId(historicoOperadorCaixaDTO1.getId());
        assertThat(historicoOperadorCaixaDTO1).isEqualTo(historicoOperadorCaixaDTO2);
        historicoOperadorCaixaDTO2.setId(2L);
        assertThat(historicoOperadorCaixaDTO1).isNotEqualTo(historicoOperadorCaixaDTO2);
        historicoOperadorCaixaDTO1.setId(null);
        assertThat(historicoOperadorCaixaDTO1).isNotEqualTo(historicoOperadorCaixaDTO2);
    }
}

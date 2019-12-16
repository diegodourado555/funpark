package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class OperadorCaixaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperadorCaixaDTO.class);
        OperadorCaixaDTO operadorCaixaDTO1 = new OperadorCaixaDTO();
        operadorCaixaDTO1.setId(1L);
        OperadorCaixaDTO operadorCaixaDTO2 = new OperadorCaixaDTO();
        assertThat(operadorCaixaDTO1).isNotEqualTo(operadorCaixaDTO2);
        operadorCaixaDTO2.setId(operadorCaixaDTO1.getId());
        assertThat(operadorCaixaDTO1).isEqualTo(operadorCaixaDTO2);
        operadorCaixaDTO2.setId(2L);
        assertThat(operadorCaixaDTO1).isNotEqualTo(operadorCaixaDTO2);
        operadorCaixaDTO1.setId(null);
        assertThat(operadorCaixaDTO1).isNotEqualTo(operadorCaixaDTO2);
    }
}

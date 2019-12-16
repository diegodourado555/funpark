package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class OperadorCaixaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperadorCaixa.class);
        OperadorCaixa operadorCaixa1 = new OperadorCaixa();
        operadorCaixa1.setId(1L);
        OperadorCaixa operadorCaixa2 = new OperadorCaixa();
        operadorCaixa2.setId(operadorCaixa1.getId());
        assertThat(operadorCaixa1).isEqualTo(operadorCaixa2);
        operadorCaixa2.setId(2L);
        assertThat(operadorCaixa1).isNotEqualTo(operadorCaixa2);
        operadorCaixa1.setId(null);
        assertThat(operadorCaixa1).isNotEqualTo(operadorCaixa2);
    }
}

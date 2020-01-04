package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class HistoricoOperadorCaixaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricoOperadorCaixa.class);
        HistoricoOperadorCaixa historicoOperadorCaixa1 = new HistoricoOperadorCaixa();
        historicoOperadorCaixa1.setId(1L);
        HistoricoOperadorCaixa historicoOperadorCaixa2 = new HistoricoOperadorCaixa();
        historicoOperadorCaixa2.setId(historicoOperadorCaixa1.getId());
        assertThat(historicoOperadorCaixa1).isEqualTo(historicoOperadorCaixa2);
        historicoOperadorCaixa2.setId(2L);
        assertThat(historicoOperadorCaixa1).isNotEqualTo(historicoOperadorCaixa2);
        historicoOperadorCaixa1.setId(null);
        assertThat(historicoOperadorCaixa1).isNotEqualTo(historicoOperadorCaixa2);
    }
}

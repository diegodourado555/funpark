package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class HistoricoMaquinaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistoricoMaquina.class);
        HistoricoMaquina historicoMaquina1 = new HistoricoMaquina();
        historicoMaquina1.setId(1L);
        HistoricoMaquina historicoMaquina2 = new HistoricoMaquina();
        historicoMaquina2.setId(historicoMaquina1.getId());
        assertThat(historicoMaquina1).isEqualTo(historicoMaquina2);
        historicoMaquina2.setId(2L);
        assertThat(historicoMaquina1).isNotEqualTo(historicoMaquina2);
        historicoMaquina1.setId(null);
        assertThat(historicoMaquina1).isNotEqualTo(historicoMaquina2);
    }
}

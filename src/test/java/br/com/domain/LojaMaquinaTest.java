package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class LojaMaquinaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LojaMaquina.class);
        LojaMaquina lojaMaquina1 = new LojaMaquina();
        lojaMaquina1.setId(1L);
        LojaMaquina lojaMaquina2 = new LojaMaquina();
        lojaMaquina2.setId(lojaMaquina1.getId());
        assertThat(lojaMaquina1).isEqualTo(lojaMaquina2);
        lojaMaquina2.setId(2L);
        assertThat(lojaMaquina1).isNotEqualTo(lojaMaquina2);
        lojaMaquina1.setId(null);
        assertThat(lojaMaquina1).isNotEqualTo(lojaMaquina2);
    }
}

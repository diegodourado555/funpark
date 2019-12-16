package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class MaquinaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Maquina.class);
        Maquina maquina1 = new Maquina();
        maquina1.setId(1L);
        Maquina maquina2 = new Maquina();
        maquina2.setId(maquina1.getId());
        assertThat(maquina1).isEqualTo(maquina2);
        maquina2.setId(2L);
        assertThat(maquina1).isNotEqualTo(maquina2);
        maquina1.setId(null);
        assertThat(maquina1).isNotEqualTo(maquina2);
    }
}

package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class GrupoMaquinaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrupoMaquina.class);
        GrupoMaquina grupoMaquina1 = new GrupoMaquina();
        grupoMaquina1.setId(1L);
        GrupoMaquina grupoMaquina2 = new GrupoMaquina();
        grupoMaquina2.setId(grupoMaquina1.getId());
        assertThat(grupoMaquina1).isEqualTo(grupoMaquina2);
        grupoMaquina2.setId(2L);
        assertThat(grupoMaquina1).isNotEqualTo(grupoMaquina2);
        grupoMaquina1.setId(null);
        assertThat(grupoMaquina1).isNotEqualTo(grupoMaquina2);
    }
}

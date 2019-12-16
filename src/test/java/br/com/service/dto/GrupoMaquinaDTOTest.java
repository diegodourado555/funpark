package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class GrupoMaquinaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GrupoMaquinaDTO.class);
        GrupoMaquinaDTO grupoMaquinaDTO1 = new GrupoMaquinaDTO();
        grupoMaquinaDTO1.setId(1L);
        GrupoMaquinaDTO grupoMaquinaDTO2 = new GrupoMaquinaDTO();
        assertThat(grupoMaquinaDTO1).isNotEqualTo(grupoMaquinaDTO2);
        grupoMaquinaDTO2.setId(grupoMaquinaDTO1.getId());
        assertThat(grupoMaquinaDTO1).isEqualTo(grupoMaquinaDTO2);
        grupoMaquinaDTO2.setId(2L);
        assertThat(grupoMaquinaDTO1).isNotEqualTo(grupoMaquinaDTO2);
        grupoMaquinaDTO1.setId(null);
        assertThat(grupoMaquinaDTO1).isNotEqualTo(grupoMaquinaDTO2);
    }
}

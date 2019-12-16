package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class MaquinaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaquinaDTO.class);
        MaquinaDTO maquinaDTO1 = new MaquinaDTO();
        maquinaDTO1.setId(1L);
        MaquinaDTO maquinaDTO2 = new MaquinaDTO();
        assertThat(maquinaDTO1).isNotEqualTo(maquinaDTO2);
        maquinaDTO2.setId(maquinaDTO1.getId());
        assertThat(maquinaDTO1).isEqualTo(maquinaDTO2);
        maquinaDTO2.setId(2L);
        assertThat(maquinaDTO1).isNotEqualTo(maquinaDTO2);
        maquinaDTO1.setId(null);
        assertThat(maquinaDTO1).isNotEqualTo(maquinaDTO2);
    }
}

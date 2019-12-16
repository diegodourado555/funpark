package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class ReceitasDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReceitasDTO.class);
        ReceitasDTO receitasDTO1 = new ReceitasDTO();
        receitasDTO1.setId(1L);
        ReceitasDTO receitasDTO2 = new ReceitasDTO();
        assertThat(receitasDTO1).isNotEqualTo(receitasDTO2);
        receitasDTO2.setId(receitasDTO1.getId());
        assertThat(receitasDTO1).isEqualTo(receitasDTO2);
        receitasDTO2.setId(2L);
        assertThat(receitasDTO1).isNotEqualTo(receitasDTO2);
        receitasDTO1.setId(null);
        assertThat(receitasDTO1).isNotEqualTo(receitasDTO2);
    }
}

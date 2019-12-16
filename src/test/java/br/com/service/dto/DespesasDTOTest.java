package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class DespesasDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DespesasDTO.class);
        DespesasDTO despesasDTO1 = new DespesasDTO();
        despesasDTO1.setId(1L);
        DespesasDTO despesasDTO2 = new DespesasDTO();
        assertThat(despesasDTO1).isNotEqualTo(despesasDTO2);
        despesasDTO2.setId(despesasDTO1.getId());
        assertThat(despesasDTO1).isEqualTo(despesasDTO2);
        despesasDTO2.setId(2L);
        assertThat(despesasDTO1).isNotEqualTo(despesasDTO2);
        despesasDTO1.setId(null);
        assertThat(despesasDTO1).isNotEqualTo(despesasDTO2);
    }
}

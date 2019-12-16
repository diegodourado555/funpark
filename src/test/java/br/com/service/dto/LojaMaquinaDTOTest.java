package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class LojaMaquinaDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LojaMaquinaDTO.class);
        LojaMaquinaDTO lojaMaquinaDTO1 = new LojaMaquinaDTO();
        lojaMaquinaDTO1.setId(1L);
        LojaMaquinaDTO lojaMaquinaDTO2 = new LojaMaquinaDTO();
        assertThat(lojaMaquinaDTO1).isNotEqualTo(lojaMaquinaDTO2);
        lojaMaquinaDTO2.setId(lojaMaquinaDTO1.getId());
        assertThat(lojaMaquinaDTO1).isEqualTo(lojaMaquinaDTO2);
        lojaMaquinaDTO2.setId(2L);
        assertThat(lojaMaquinaDTO1).isNotEqualTo(lojaMaquinaDTO2);
        lojaMaquinaDTO1.setId(null);
        assertThat(lojaMaquinaDTO1).isNotEqualTo(lojaMaquinaDTO2);
    }
}

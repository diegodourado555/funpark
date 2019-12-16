package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class ContaCorrenteDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContaCorrenteDTO.class);
        ContaCorrenteDTO contaCorrenteDTO1 = new ContaCorrenteDTO();
        contaCorrenteDTO1.setId(1L);
        ContaCorrenteDTO contaCorrenteDTO2 = new ContaCorrenteDTO();
        assertThat(contaCorrenteDTO1).isNotEqualTo(contaCorrenteDTO2);
        contaCorrenteDTO2.setId(contaCorrenteDTO1.getId());
        assertThat(contaCorrenteDTO1).isEqualTo(contaCorrenteDTO2);
        contaCorrenteDTO2.setId(2L);
        assertThat(contaCorrenteDTO1).isNotEqualTo(contaCorrenteDTO2);
        contaCorrenteDTO1.setId(null);
        assertThat(contaCorrenteDTO1).isNotEqualTo(contaCorrenteDTO2);
    }
}

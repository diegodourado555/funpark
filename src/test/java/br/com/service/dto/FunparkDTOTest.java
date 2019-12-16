package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class FunparkDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FunparkDTO.class);
        FunparkDTO funparkDTO1 = new FunparkDTO();
        funparkDTO1.setId(1L);
        FunparkDTO funparkDTO2 = new FunparkDTO();
        assertThat(funparkDTO1).isNotEqualTo(funparkDTO2);
        funparkDTO2.setId(funparkDTO1.getId());
        assertThat(funparkDTO1).isEqualTo(funparkDTO2);
        funparkDTO2.setId(2L);
        assertThat(funparkDTO1).isNotEqualTo(funparkDTO2);
        funparkDTO1.setId(null);
        assertThat(funparkDTO1).isNotEqualTo(funparkDTO2);
    }
}

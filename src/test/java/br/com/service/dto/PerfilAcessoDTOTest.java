package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class PerfilAcessoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PerfilAcessoDTO.class);
        PerfilAcessoDTO perfilAcessoDTO1 = new PerfilAcessoDTO();
        perfilAcessoDTO1.setId(1L);
        PerfilAcessoDTO perfilAcessoDTO2 = new PerfilAcessoDTO();
        assertThat(perfilAcessoDTO1).isNotEqualTo(perfilAcessoDTO2);
        perfilAcessoDTO2.setId(perfilAcessoDTO1.getId());
        assertThat(perfilAcessoDTO1).isEqualTo(perfilAcessoDTO2);
        perfilAcessoDTO2.setId(2L);
        assertThat(perfilAcessoDTO1).isNotEqualTo(perfilAcessoDTO2);
        perfilAcessoDTO1.setId(null);
        assertThat(perfilAcessoDTO1).isNotEqualTo(perfilAcessoDTO2);
    }
}

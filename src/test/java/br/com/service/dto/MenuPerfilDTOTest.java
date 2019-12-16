package br.com.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class MenuPerfilDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuPerfilDTO.class);
        MenuPerfilDTO menuPerfilDTO1 = new MenuPerfilDTO();
        menuPerfilDTO1.setId(1L);
        MenuPerfilDTO menuPerfilDTO2 = new MenuPerfilDTO();
        assertThat(menuPerfilDTO1).isNotEqualTo(menuPerfilDTO2);
        menuPerfilDTO2.setId(menuPerfilDTO1.getId());
        assertThat(menuPerfilDTO1).isEqualTo(menuPerfilDTO2);
        menuPerfilDTO2.setId(2L);
        assertThat(menuPerfilDTO1).isNotEqualTo(menuPerfilDTO2);
        menuPerfilDTO1.setId(null);
        assertThat(menuPerfilDTO1).isNotEqualTo(menuPerfilDTO2);
    }
}

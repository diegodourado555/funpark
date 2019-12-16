package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class MenuPerfilTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MenuPerfil.class);
        MenuPerfil menuPerfil1 = new MenuPerfil();
        menuPerfil1.setId(1L);
        MenuPerfil menuPerfil2 = new MenuPerfil();
        menuPerfil2.setId(menuPerfil1.getId());
        assertThat(menuPerfil1).isEqualTo(menuPerfil2);
        menuPerfil2.setId(2L);
        assertThat(menuPerfil1).isNotEqualTo(menuPerfil2);
        menuPerfil1.setId(null);
        assertThat(menuPerfil1).isNotEqualTo(menuPerfil2);
    }
}

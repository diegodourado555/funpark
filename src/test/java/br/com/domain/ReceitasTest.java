package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class ReceitasTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Receitas.class);
        Receitas receitas1 = new Receitas();
        receitas1.setId(1L);
        Receitas receitas2 = new Receitas();
        receitas2.setId(receitas1.getId());
        assertThat(receitas1).isEqualTo(receitas2);
        receitas2.setId(2L);
        assertThat(receitas1).isNotEqualTo(receitas2);
        receitas1.setId(null);
        assertThat(receitas1).isNotEqualTo(receitas2);
    }
}

package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class FunparkTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Funpark.class);
        Funpark funpark1 = new Funpark();
        funpark1.setId(1L);
        Funpark funpark2 = new Funpark();
        funpark2.setId(funpark1.getId());
        assertThat(funpark1).isEqualTo(funpark2);
        funpark2.setId(2L);
        assertThat(funpark1).isNotEqualTo(funpark2);
        funpark1.setId(null);
        assertThat(funpark1).isNotEqualTo(funpark2);
    }
}

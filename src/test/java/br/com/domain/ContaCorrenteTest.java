package br.com.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.web.rest.TestUtil;

public class ContaCorrenteTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ContaCorrente.class);
        ContaCorrente contaCorrente1 = new ContaCorrente();
        contaCorrente1.setId(1L);
        ContaCorrente contaCorrente2 = new ContaCorrente();
        contaCorrente2.setId(contaCorrente1.getId());
        assertThat(contaCorrente1).isEqualTo(contaCorrente2);
        contaCorrente2.setId(2L);
        assertThat(contaCorrente1).isNotEqualTo(contaCorrente2);
        contaCorrente1.setId(null);
        assertThat(contaCorrente1).isNotEqualTo(contaCorrente2);
    }
}

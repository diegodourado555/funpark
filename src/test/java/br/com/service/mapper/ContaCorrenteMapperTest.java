package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ContaCorrenteMapperTest {

    private ContaCorrenteMapper contaCorrenteMapper;

    @BeforeEach
    public void setUp() {
        contaCorrenteMapper = new ContaCorrenteMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(contaCorrenteMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(contaCorrenteMapper.fromId(null)).isNull();
    }
}

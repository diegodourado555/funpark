package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class OperadorCaixaMapperTest {

    private OperadorCaixaMapper operadorCaixaMapper;

    @BeforeEach
    public void setUp() {
        operadorCaixaMapper = new OperadorCaixaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(operadorCaixaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(operadorCaixaMapper.fromId(null)).isNull();
    }
}

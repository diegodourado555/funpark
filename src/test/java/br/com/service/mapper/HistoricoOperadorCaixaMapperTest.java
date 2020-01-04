package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class HistoricoOperadorCaixaMapperTest {

    private HistoricoOperadorCaixaMapper historicoOperadorCaixaMapper;

    @BeforeEach
    public void setUp() {
        historicoOperadorCaixaMapper = new HistoricoOperadorCaixaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(historicoOperadorCaixaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(historicoOperadorCaixaMapper.fromId(null)).isNull();
    }
}

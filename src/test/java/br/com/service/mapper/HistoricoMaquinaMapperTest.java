package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class HistoricoMaquinaMapperTest {

    private HistoricoMaquinaMapper historicoMaquinaMapper;

    @BeforeEach
    public void setUp() {
        historicoMaquinaMapper = new HistoricoMaquinaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(historicoMaquinaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(historicoMaquinaMapper.fromId(null)).isNull();
    }
}

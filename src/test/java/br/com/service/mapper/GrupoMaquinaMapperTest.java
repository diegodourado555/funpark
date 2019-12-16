package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class GrupoMaquinaMapperTest {

    private GrupoMaquinaMapper grupoMaquinaMapper;

    @BeforeEach
    public void setUp() {
        grupoMaquinaMapper = new GrupoMaquinaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(grupoMaquinaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(grupoMaquinaMapper.fromId(null)).isNull();
    }
}

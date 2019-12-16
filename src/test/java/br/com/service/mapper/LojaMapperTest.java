package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LojaMapperTest {

    private LojaMapper lojaMapper;

    @BeforeEach
    public void setUp() {
        lojaMapper = new LojaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(lojaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lojaMapper.fromId(null)).isNull();
    }
}

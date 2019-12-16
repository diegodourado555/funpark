package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class LojaMaquinaMapperTest {

    private LojaMaquinaMapper lojaMaquinaMapper;

    @BeforeEach
    public void setUp() {
        lojaMaquinaMapper = new LojaMaquinaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(lojaMaquinaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lojaMaquinaMapper.fromId(null)).isNull();
    }
}

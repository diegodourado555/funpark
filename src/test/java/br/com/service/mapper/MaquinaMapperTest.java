package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MaquinaMapperTest {

    private MaquinaMapper maquinaMapper;

    @BeforeEach
    public void setUp() {
        maquinaMapper = new MaquinaMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(maquinaMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(maquinaMapper.fromId(null)).isNull();
    }
}

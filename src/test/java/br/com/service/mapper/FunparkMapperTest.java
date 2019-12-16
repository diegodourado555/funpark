package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class FunparkMapperTest {

    private FunparkMapper funparkMapper;

    @BeforeEach
    public void setUp() {
        funparkMapper = new FunparkMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(funparkMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(funparkMapper.fromId(null)).isNull();
    }
}

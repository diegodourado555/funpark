package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class ReceitasMapperTest {

    private ReceitasMapper receitasMapper;

    @BeforeEach
    public void setUp() {
        receitasMapper = new ReceitasMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(receitasMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(receitasMapper.fromId(null)).isNull();
    }
}

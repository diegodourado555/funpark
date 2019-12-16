package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DespesasMapperTest {

    private DespesasMapper despesasMapper;

    @BeforeEach
    public void setUp() {
        despesasMapper = new DespesasMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(despesasMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(despesasMapper.fromId(null)).isNull();
    }
}

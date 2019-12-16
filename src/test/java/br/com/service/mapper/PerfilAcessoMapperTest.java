package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class PerfilAcessoMapperTest {

    private PerfilAcessoMapper perfilAcessoMapper;

    @BeforeEach
    public void setUp() {
        perfilAcessoMapper = new PerfilAcessoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(perfilAcessoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(perfilAcessoMapper.fromId(null)).isNull();
    }
}

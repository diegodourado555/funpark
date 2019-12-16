package br.com.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class MenuPerfilMapperTest {

    private MenuPerfilMapper menuPerfilMapper;

    @BeforeEach
    public void setUp() {
        menuPerfilMapper = new MenuPerfilMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(menuPerfilMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(menuPerfilMapper.fromId(null)).isNull();
    }
}

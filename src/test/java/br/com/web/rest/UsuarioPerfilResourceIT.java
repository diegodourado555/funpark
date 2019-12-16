package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.UsuarioPerfil;
import br.com.repository.UsuarioPerfilRepository;
import br.com.service.UsuarioPerfilService;
import br.com.service.dto.UsuarioPerfilDTO;
import br.com.service.mapper.UsuarioPerfilMapper;
import br.com.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static br.com.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link UsuarioPerfilResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class UsuarioPerfilResourceIT {

    private static final Long DEFAULT_ID_USUARIO = 1L;
    private static final Long UPDATED_ID_USUARIO = 2L;

    private static final Long DEFAULT_ID_PERFIL = 1L;
    private static final Long UPDATED_ID_PERFIL = 2L;

    @Autowired
    private UsuarioPerfilRepository usuarioPerfilRepository;

    @Autowired
    private UsuarioPerfilMapper usuarioPerfilMapper;

    @Autowired
    private UsuarioPerfilService usuarioPerfilService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restUsuarioPerfilMockMvc;

    private UsuarioPerfil usuarioPerfil;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UsuarioPerfilResource usuarioPerfilResource = new UsuarioPerfilResource(usuarioPerfilService);
        this.restUsuarioPerfilMockMvc = MockMvcBuilders.standaloneSetup(usuarioPerfilResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuarioPerfil createEntity(EntityManager em) {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil()
            .idUsuario(DEFAULT_ID_USUARIO)
            .idPerfil(DEFAULT_ID_PERFIL);
        return usuarioPerfil;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UsuarioPerfil createUpdatedEntity(EntityManager em) {
        UsuarioPerfil usuarioPerfil = new UsuarioPerfil()
            .idUsuario(UPDATED_ID_USUARIO)
            .idPerfil(UPDATED_ID_PERFIL);
        return usuarioPerfil;
    }

    @BeforeEach
    public void initTest() {
        usuarioPerfil = createEntity(em);
    }

    @Test
    @Transactional
    public void createUsuarioPerfil() throws Exception {
        int databaseSizeBeforeCreate = usuarioPerfilRepository.findAll().size();

        // Create the UsuarioPerfil
        UsuarioPerfilDTO usuarioPerfilDTO = usuarioPerfilMapper.toDto(usuarioPerfil);
        restUsuarioPerfilMockMvc.perform(post("/api/usuario-perfils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioPerfilDTO)))
            .andExpect(status().isCreated());

        // Validate the UsuarioPerfil in the database
        List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilRepository.findAll();
        assertThat(usuarioPerfilList).hasSize(databaseSizeBeforeCreate + 1);
        UsuarioPerfil testUsuarioPerfil = usuarioPerfilList.get(usuarioPerfilList.size() - 1);
        assertThat(testUsuarioPerfil.getIdUsuario()).isEqualTo(DEFAULT_ID_USUARIO);
        assertThat(testUsuarioPerfil.getIdPerfil()).isEqualTo(DEFAULT_ID_PERFIL);
    }

    @Test
    @Transactional
    public void createUsuarioPerfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = usuarioPerfilRepository.findAll().size();

        // Create the UsuarioPerfil with an existing ID
        usuarioPerfil.setId(1L);
        UsuarioPerfilDTO usuarioPerfilDTO = usuarioPerfilMapper.toDto(usuarioPerfil);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUsuarioPerfilMockMvc.perform(post("/api/usuario-perfils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioPerfilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UsuarioPerfil in the database
        List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilRepository.findAll();
        assertThat(usuarioPerfilList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllUsuarioPerfils() throws Exception {
        // Initialize the database
        usuarioPerfilRepository.saveAndFlush(usuarioPerfil);

        // Get all the usuarioPerfilList
        restUsuarioPerfilMockMvc.perform(get("/api/usuario-perfils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(usuarioPerfil.getId().intValue())))
            .andExpect(jsonPath("$.[*].idUsuario").value(hasItem(DEFAULT_ID_USUARIO.intValue())))
            .andExpect(jsonPath("$.[*].idPerfil").value(hasItem(DEFAULT_ID_PERFIL.intValue())));
    }
    
    @Test
    @Transactional
    public void getUsuarioPerfil() throws Exception {
        // Initialize the database
        usuarioPerfilRepository.saveAndFlush(usuarioPerfil);

        // Get the usuarioPerfil
        restUsuarioPerfilMockMvc.perform(get("/api/usuario-perfils/{id}", usuarioPerfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(usuarioPerfil.getId().intValue()))
            .andExpect(jsonPath("$.idUsuario").value(DEFAULT_ID_USUARIO.intValue()))
            .andExpect(jsonPath("$.idPerfil").value(DEFAULT_ID_PERFIL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUsuarioPerfil() throws Exception {
        // Get the usuarioPerfil
        restUsuarioPerfilMockMvc.perform(get("/api/usuario-perfils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUsuarioPerfil() throws Exception {
        // Initialize the database
        usuarioPerfilRepository.saveAndFlush(usuarioPerfil);

        int databaseSizeBeforeUpdate = usuarioPerfilRepository.findAll().size();

        // Update the usuarioPerfil
        UsuarioPerfil updatedUsuarioPerfil = usuarioPerfilRepository.findById(usuarioPerfil.getId()).get();
        // Disconnect from session so that the updates on updatedUsuarioPerfil are not directly saved in db
        em.detach(updatedUsuarioPerfil);
        updatedUsuarioPerfil
            .idUsuario(UPDATED_ID_USUARIO)
            .idPerfil(UPDATED_ID_PERFIL);
        UsuarioPerfilDTO usuarioPerfilDTO = usuarioPerfilMapper.toDto(updatedUsuarioPerfil);

        restUsuarioPerfilMockMvc.perform(put("/api/usuario-perfils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioPerfilDTO)))
            .andExpect(status().isOk());

        // Validate the UsuarioPerfil in the database
        List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilRepository.findAll();
        assertThat(usuarioPerfilList).hasSize(databaseSizeBeforeUpdate);
        UsuarioPerfil testUsuarioPerfil = usuarioPerfilList.get(usuarioPerfilList.size() - 1);
        assertThat(testUsuarioPerfil.getIdUsuario()).isEqualTo(UPDATED_ID_USUARIO);
        assertThat(testUsuarioPerfil.getIdPerfil()).isEqualTo(UPDATED_ID_PERFIL);
    }

    @Test
    @Transactional
    public void updateNonExistingUsuarioPerfil() throws Exception {
        int databaseSizeBeforeUpdate = usuarioPerfilRepository.findAll().size();

        // Create the UsuarioPerfil
        UsuarioPerfilDTO usuarioPerfilDTO = usuarioPerfilMapper.toDto(usuarioPerfil);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restUsuarioPerfilMockMvc.perform(put("/api/usuario-perfils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(usuarioPerfilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UsuarioPerfil in the database
        List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilRepository.findAll();
        assertThat(usuarioPerfilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteUsuarioPerfil() throws Exception {
        // Initialize the database
        usuarioPerfilRepository.saveAndFlush(usuarioPerfil);

        int databaseSizeBeforeDelete = usuarioPerfilRepository.findAll().size();

        // Delete the usuarioPerfil
        restUsuarioPerfilMockMvc.perform(delete("/api/usuario-perfils/{id}", usuarioPerfil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<UsuarioPerfil> usuarioPerfilList = usuarioPerfilRepository.findAll();
        assertThat(usuarioPerfilList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

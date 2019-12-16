package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.GrupoMaquina;
import br.com.repository.GrupoMaquinaRepository;
import br.com.service.GrupoMaquinaService;
import br.com.service.dto.GrupoMaquinaDTO;
import br.com.service.mapper.GrupoMaquinaMapper;
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
 * Integration tests for the {@link GrupoMaquinaResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class GrupoMaquinaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private GrupoMaquinaRepository grupoMaquinaRepository;

    @Autowired
    private GrupoMaquinaMapper grupoMaquinaMapper;

    @Autowired
    private GrupoMaquinaService grupoMaquinaService;

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

    private MockMvc restGrupoMaquinaMockMvc;

    private GrupoMaquina grupoMaquina;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GrupoMaquinaResource grupoMaquinaResource = new GrupoMaquinaResource(grupoMaquinaService);
        this.restGrupoMaquinaMockMvc = MockMvcBuilders.standaloneSetup(grupoMaquinaResource)
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
    public static GrupoMaquina createEntity(EntityManager em) {
        GrupoMaquina grupoMaquina = new GrupoMaquina()
            .nome(DEFAULT_NOME);
        return grupoMaquina;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static GrupoMaquina createUpdatedEntity(EntityManager em) {
        GrupoMaquina grupoMaquina = new GrupoMaquina()
            .nome(UPDATED_NOME);
        return grupoMaquina;
    }

    @BeforeEach
    public void initTest() {
        grupoMaquina = createEntity(em);
    }

    @Test
    @Transactional
    public void createGrupoMaquina() throws Exception {
        int databaseSizeBeforeCreate = grupoMaquinaRepository.findAll().size();

        // Create the GrupoMaquina
        GrupoMaquinaDTO grupoMaquinaDTO = grupoMaquinaMapper.toDto(grupoMaquina);
        restGrupoMaquinaMockMvc.perform(post("/api/grupo-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoMaquinaDTO)))
            .andExpect(status().isCreated());

        // Validate the GrupoMaquina in the database
        List<GrupoMaquina> grupoMaquinaList = grupoMaquinaRepository.findAll();
        assertThat(grupoMaquinaList).hasSize(databaseSizeBeforeCreate + 1);
        GrupoMaquina testGrupoMaquina = grupoMaquinaList.get(grupoMaquinaList.size() - 1);
        assertThat(testGrupoMaquina.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createGrupoMaquinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = grupoMaquinaRepository.findAll().size();

        // Create the GrupoMaquina with an existing ID
        grupoMaquina.setId(1L);
        GrupoMaquinaDTO grupoMaquinaDTO = grupoMaquinaMapper.toDto(grupoMaquina);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGrupoMaquinaMockMvc.perform(post("/api/grupo-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoMaquinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoMaquina in the database
        List<GrupoMaquina> grupoMaquinaList = grupoMaquinaRepository.findAll();
        assertThat(grupoMaquinaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllGrupoMaquinas() throws Exception {
        // Initialize the database
        grupoMaquinaRepository.saveAndFlush(grupoMaquina);

        // Get all the grupoMaquinaList
        restGrupoMaquinaMockMvc.perform(get("/api/grupo-maquinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(grupoMaquina.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)));
    }
    
    @Test
    @Transactional
    public void getGrupoMaquina() throws Exception {
        // Initialize the database
        grupoMaquinaRepository.saveAndFlush(grupoMaquina);

        // Get the grupoMaquina
        restGrupoMaquinaMockMvc.perform(get("/api/grupo-maquinas/{id}", grupoMaquina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(grupoMaquina.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME));
    }

    @Test
    @Transactional
    public void getNonExistingGrupoMaquina() throws Exception {
        // Get the grupoMaquina
        restGrupoMaquinaMockMvc.perform(get("/api/grupo-maquinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGrupoMaquina() throws Exception {
        // Initialize the database
        grupoMaquinaRepository.saveAndFlush(grupoMaquina);

        int databaseSizeBeforeUpdate = grupoMaquinaRepository.findAll().size();

        // Update the grupoMaquina
        GrupoMaquina updatedGrupoMaquina = grupoMaquinaRepository.findById(grupoMaquina.getId()).get();
        // Disconnect from session so that the updates on updatedGrupoMaquina are not directly saved in db
        em.detach(updatedGrupoMaquina);
        updatedGrupoMaquina
            .nome(UPDATED_NOME);
        GrupoMaquinaDTO grupoMaquinaDTO = grupoMaquinaMapper.toDto(updatedGrupoMaquina);

        restGrupoMaquinaMockMvc.perform(put("/api/grupo-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoMaquinaDTO)))
            .andExpect(status().isOk());

        // Validate the GrupoMaquina in the database
        List<GrupoMaquina> grupoMaquinaList = grupoMaquinaRepository.findAll();
        assertThat(grupoMaquinaList).hasSize(databaseSizeBeforeUpdate);
        GrupoMaquina testGrupoMaquina = grupoMaquinaList.get(grupoMaquinaList.size() - 1);
        assertThat(testGrupoMaquina.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingGrupoMaquina() throws Exception {
        int databaseSizeBeforeUpdate = grupoMaquinaRepository.findAll().size();

        // Create the GrupoMaquina
        GrupoMaquinaDTO grupoMaquinaDTO = grupoMaquinaMapper.toDto(grupoMaquina);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGrupoMaquinaMockMvc.perform(put("/api/grupo-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(grupoMaquinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the GrupoMaquina in the database
        List<GrupoMaquina> grupoMaquinaList = grupoMaquinaRepository.findAll();
        assertThat(grupoMaquinaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGrupoMaquina() throws Exception {
        // Initialize the database
        grupoMaquinaRepository.saveAndFlush(grupoMaquina);

        int databaseSizeBeforeDelete = grupoMaquinaRepository.findAll().size();

        // Delete the grupoMaquina
        restGrupoMaquinaMockMvc.perform(delete("/api/grupo-maquinas/{id}", grupoMaquina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<GrupoMaquina> grupoMaquinaList = grupoMaquinaRepository.findAll();
        assertThat(grupoMaquinaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

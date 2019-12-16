package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.Maquina;
import br.com.repository.MaquinaRepository;
import br.com.service.MaquinaService;
import br.com.service.dto.MaquinaDTO;
import br.com.service.mapper.MaquinaMapper;
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
 * Integration tests for the {@link MaquinaResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class MaquinaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Long DEFAULT_ID_GRUPO_MAQUINA = 1L;
    private static final Long UPDATED_ID_GRUPO_MAQUINA = 2L;

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Autowired
    private MaquinaMapper maquinaMapper;

    @Autowired
    private MaquinaService maquinaService;

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

    private MockMvc restMaquinaMockMvc;

    private Maquina maquina;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MaquinaResource maquinaResource = new MaquinaResource(maquinaService);
        this.restMaquinaMockMvc = MockMvcBuilders.standaloneSetup(maquinaResource)
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
    public static Maquina createEntity(EntityManager em) {
        Maquina maquina = new Maquina()
            .nome(DEFAULT_NOME)
            .idGrupoMaquina(DEFAULT_ID_GRUPO_MAQUINA);
        return maquina;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Maquina createUpdatedEntity(EntityManager em) {
        Maquina maquina = new Maquina()
            .nome(UPDATED_NOME)
            .idGrupoMaquina(UPDATED_ID_GRUPO_MAQUINA);
        return maquina;
    }

    @BeforeEach
    public void initTest() {
        maquina = createEntity(em);
    }

    @Test
    @Transactional
    public void createMaquina() throws Exception {
        int databaseSizeBeforeCreate = maquinaRepository.findAll().size();

        // Create the Maquina
        MaquinaDTO maquinaDTO = maquinaMapper.toDto(maquina);
        restMaquinaMockMvc.perform(post("/api/maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maquinaDTO)))
            .andExpect(status().isCreated());

        // Validate the Maquina in the database
        List<Maquina> maquinaList = maquinaRepository.findAll();
        assertThat(maquinaList).hasSize(databaseSizeBeforeCreate + 1);
        Maquina testMaquina = maquinaList.get(maquinaList.size() - 1);
        assertThat(testMaquina.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testMaquina.getIdGrupoMaquina()).isEqualTo(DEFAULT_ID_GRUPO_MAQUINA);
    }

    @Test
    @Transactional
    public void createMaquinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maquinaRepository.findAll().size();

        // Create the Maquina with an existing ID
        maquina.setId(1L);
        MaquinaDTO maquinaDTO = maquinaMapper.toDto(maquina);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaquinaMockMvc.perform(post("/api/maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maquinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Maquina in the database
        List<Maquina> maquinaList = maquinaRepository.findAll();
        assertThat(maquinaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMaquinas() throws Exception {
        // Initialize the database
        maquinaRepository.saveAndFlush(maquina);

        // Get all the maquinaList
        restMaquinaMockMvc.perform(get("/api/maquinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(maquina.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].idGrupoMaquina").value(hasItem(DEFAULT_ID_GRUPO_MAQUINA.intValue())));
    }
    
    @Test
    @Transactional
    public void getMaquina() throws Exception {
        // Initialize the database
        maquinaRepository.saveAndFlush(maquina);

        // Get the maquina
        restMaquinaMockMvc.perform(get("/api/maquinas/{id}", maquina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(maquina.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.idGrupoMaquina").value(DEFAULT_ID_GRUPO_MAQUINA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMaquina() throws Exception {
        // Get the maquina
        restMaquinaMockMvc.perform(get("/api/maquinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMaquina() throws Exception {
        // Initialize the database
        maquinaRepository.saveAndFlush(maquina);

        int databaseSizeBeforeUpdate = maquinaRepository.findAll().size();

        // Update the maquina
        Maquina updatedMaquina = maquinaRepository.findById(maquina.getId()).get();
        // Disconnect from session so that the updates on updatedMaquina are not directly saved in db
        em.detach(updatedMaquina);
        updatedMaquina
            .nome(UPDATED_NOME)
            .idGrupoMaquina(UPDATED_ID_GRUPO_MAQUINA);
        MaquinaDTO maquinaDTO = maquinaMapper.toDto(updatedMaquina);

        restMaquinaMockMvc.perform(put("/api/maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maquinaDTO)))
            .andExpect(status().isOk());

        // Validate the Maquina in the database
        List<Maquina> maquinaList = maquinaRepository.findAll();
        assertThat(maquinaList).hasSize(databaseSizeBeforeUpdate);
        Maquina testMaquina = maquinaList.get(maquinaList.size() - 1);
        assertThat(testMaquina.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testMaquina.getIdGrupoMaquina()).isEqualTo(UPDATED_ID_GRUPO_MAQUINA);
    }

    @Test
    @Transactional
    public void updateNonExistingMaquina() throws Exception {
        int databaseSizeBeforeUpdate = maquinaRepository.findAll().size();

        // Create the Maquina
        MaquinaDTO maquinaDTO = maquinaMapper.toDto(maquina);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMaquinaMockMvc.perform(put("/api/maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maquinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Maquina in the database
        List<Maquina> maquinaList = maquinaRepository.findAll();
        assertThat(maquinaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMaquina() throws Exception {
        // Initialize the database
        maquinaRepository.saveAndFlush(maquina);

        int databaseSizeBeforeDelete = maquinaRepository.findAll().size();

        // Delete the maquina
        restMaquinaMockMvc.perform(delete("/api/maquinas/{id}", maquina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Maquina> maquinaList = maquinaRepository.findAll();
        assertThat(maquinaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

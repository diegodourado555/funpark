package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.LojaMaquina;
import br.com.repository.LojaMaquinaRepository;
import br.com.service.LojaMaquinaService;
import br.com.service.dto.LojaMaquinaDTO;
import br.com.service.mapper.LojaMaquinaMapper;
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
 * Integration tests for the {@link LojaMaquinaResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class LojaMaquinaResourceIT {

    private static final Long DEFAULT_ID_LOJA = 1L;
    private static final Long UPDATED_ID_LOJA = 2L;

    private static final Long DEFAULT_ID_MAQUINA = 1L;
    private static final Long UPDATED_ID_MAQUINA = 2L;

    @Autowired
    private LojaMaquinaRepository lojaMaquinaRepository;

    @Autowired
    private LojaMaquinaMapper lojaMaquinaMapper;

    @Autowired
    private LojaMaquinaService lojaMaquinaService;

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

    private MockMvc restLojaMaquinaMockMvc;

    private LojaMaquina lojaMaquina;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LojaMaquinaResource lojaMaquinaResource = new LojaMaquinaResource(lojaMaquinaService);
        this.restLojaMaquinaMockMvc = MockMvcBuilders.standaloneSetup(lojaMaquinaResource)
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
    public static LojaMaquina createEntity(EntityManager em) {
        LojaMaquina lojaMaquina = new LojaMaquina()
            .idLoja(DEFAULT_ID_LOJA)
            .idMaquina(DEFAULT_ID_MAQUINA);
        return lojaMaquina;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LojaMaquina createUpdatedEntity(EntityManager em) {
        LojaMaquina lojaMaquina = new LojaMaquina()
            .idLoja(UPDATED_ID_LOJA)
            .idMaquina(UPDATED_ID_MAQUINA);
        return lojaMaquina;
    }

    @BeforeEach
    public void initTest() {
        lojaMaquina = createEntity(em);
    }

    @Test
    @Transactional
    public void createLojaMaquina() throws Exception {
        int databaseSizeBeforeCreate = lojaMaquinaRepository.findAll().size();

        // Create the LojaMaquina
        LojaMaquinaDTO lojaMaquinaDTO = lojaMaquinaMapper.toDto(lojaMaquina);
        restLojaMaquinaMockMvc.perform(post("/api/loja-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lojaMaquinaDTO)))
            .andExpect(status().isCreated());

        // Validate the LojaMaquina in the database
        List<LojaMaquina> lojaMaquinaList = lojaMaquinaRepository.findAll();
        assertThat(lojaMaquinaList).hasSize(databaseSizeBeforeCreate + 1);
        LojaMaquina testLojaMaquina = lojaMaquinaList.get(lojaMaquinaList.size() - 1);
        assertThat(testLojaMaquina.getIdLoja()).isEqualTo(DEFAULT_ID_LOJA);
        assertThat(testLojaMaquina.getIdMaquina()).isEqualTo(DEFAULT_ID_MAQUINA);
    }

    @Test
    @Transactional
    public void createLojaMaquinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lojaMaquinaRepository.findAll().size();

        // Create the LojaMaquina with an existing ID
        lojaMaquina.setId(1L);
        LojaMaquinaDTO lojaMaquinaDTO = lojaMaquinaMapper.toDto(lojaMaquina);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLojaMaquinaMockMvc.perform(post("/api/loja-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lojaMaquinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LojaMaquina in the database
        List<LojaMaquina> lojaMaquinaList = lojaMaquinaRepository.findAll();
        assertThat(lojaMaquinaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLojaMaquinas() throws Exception {
        // Initialize the database
        lojaMaquinaRepository.saveAndFlush(lojaMaquina);

        // Get all the lojaMaquinaList
        restLojaMaquinaMockMvc.perform(get("/api/loja-maquinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lojaMaquina.getId().intValue())))
            .andExpect(jsonPath("$.[*].idLoja").value(hasItem(DEFAULT_ID_LOJA.intValue())))
            .andExpect(jsonPath("$.[*].idMaquina").value(hasItem(DEFAULT_ID_MAQUINA.intValue())));
    }
    
    @Test
    @Transactional
    public void getLojaMaquina() throws Exception {
        // Initialize the database
        lojaMaquinaRepository.saveAndFlush(lojaMaquina);

        // Get the lojaMaquina
        restLojaMaquinaMockMvc.perform(get("/api/loja-maquinas/{id}", lojaMaquina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lojaMaquina.getId().intValue()))
            .andExpect(jsonPath("$.idLoja").value(DEFAULT_ID_LOJA.intValue()))
            .andExpect(jsonPath("$.idMaquina").value(DEFAULT_ID_MAQUINA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingLojaMaquina() throws Exception {
        // Get the lojaMaquina
        restLojaMaquinaMockMvc.perform(get("/api/loja-maquinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLojaMaquina() throws Exception {
        // Initialize the database
        lojaMaquinaRepository.saveAndFlush(lojaMaquina);

        int databaseSizeBeforeUpdate = lojaMaquinaRepository.findAll().size();

        // Update the lojaMaquina
        LojaMaquina updatedLojaMaquina = lojaMaquinaRepository.findById(lojaMaquina.getId()).get();
        // Disconnect from session so that the updates on updatedLojaMaquina are not directly saved in db
        em.detach(updatedLojaMaquina);
        updatedLojaMaquina
            .idLoja(UPDATED_ID_LOJA)
            .idMaquina(UPDATED_ID_MAQUINA);
        LojaMaquinaDTO lojaMaquinaDTO = lojaMaquinaMapper.toDto(updatedLojaMaquina);

        restLojaMaquinaMockMvc.perform(put("/api/loja-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lojaMaquinaDTO)))
            .andExpect(status().isOk());

        // Validate the LojaMaquina in the database
        List<LojaMaquina> lojaMaquinaList = lojaMaquinaRepository.findAll();
        assertThat(lojaMaquinaList).hasSize(databaseSizeBeforeUpdate);
        LojaMaquina testLojaMaquina = lojaMaquinaList.get(lojaMaquinaList.size() - 1);
        assertThat(testLojaMaquina.getIdLoja()).isEqualTo(UPDATED_ID_LOJA);
        assertThat(testLojaMaquina.getIdMaquina()).isEqualTo(UPDATED_ID_MAQUINA);
    }

    @Test
    @Transactional
    public void updateNonExistingLojaMaquina() throws Exception {
        int databaseSizeBeforeUpdate = lojaMaquinaRepository.findAll().size();

        // Create the LojaMaquina
        LojaMaquinaDTO lojaMaquinaDTO = lojaMaquinaMapper.toDto(lojaMaquina);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLojaMaquinaMockMvc.perform(put("/api/loja-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lojaMaquinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LojaMaquina in the database
        List<LojaMaquina> lojaMaquinaList = lojaMaquinaRepository.findAll();
        assertThat(lojaMaquinaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLojaMaquina() throws Exception {
        // Initialize the database
        lojaMaquinaRepository.saveAndFlush(lojaMaquina);

        int databaseSizeBeforeDelete = lojaMaquinaRepository.findAll().size();

        // Delete the lojaMaquina
        restLojaMaquinaMockMvc.perform(delete("/api/loja-maquinas/{id}", lojaMaquina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LojaMaquina> lojaMaquinaList = lojaMaquinaRepository.findAll();
        assertThat(lojaMaquinaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

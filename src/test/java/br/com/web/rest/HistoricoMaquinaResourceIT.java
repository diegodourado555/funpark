package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.HistoricoMaquina;
import br.com.repository.HistoricoMaquinaRepository;
import br.com.service.HistoricoMaquinaService;
import br.com.service.dto.HistoricoMaquinaDTO;
import br.com.service.mapper.HistoricoMaquinaMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static br.com.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.domain.enumeration.SituacaoMaquina;
/**
 * Integration tests for the {@link HistoricoMaquinaResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class HistoricoMaquinaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final SituacaoMaquina DEFAULT_SITUACAO = SituacaoMaquina.ATIVO;
    private static final SituacaoMaquina UPDATED_SITUACAO = SituacaoMaquina.INATIVO;

    @Autowired
    private HistoricoMaquinaRepository historicoMaquinaRepository;

    @Autowired
    private HistoricoMaquinaMapper historicoMaquinaMapper;

    @Autowired
    private HistoricoMaquinaService historicoMaquinaService;

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

    private MockMvc restHistoricoMaquinaMockMvc;

    private HistoricoMaquina historicoMaquina;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HistoricoMaquinaResource historicoMaquinaResource = new HistoricoMaquinaResource(historicoMaquinaService);
        this.restHistoricoMaquinaMockMvc = MockMvcBuilders.standaloneSetup(historicoMaquinaResource)
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
    public static HistoricoMaquina createEntity(EntityManager em) {
        HistoricoMaquina historicoMaquina = new HistoricoMaquina()
            .nome(DEFAULT_NOME)
            .data(DEFAULT_DATA)
            .situacao(DEFAULT_SITUACAO);
        return historicoMaquina;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoricoMaquina createUpdatedEntity(EntityManager em) {
        HistoricoMaquina historicoMaquina = new HistoricoMaquina()
            .nome(UPDATED_NOME)
            .data(UPDATED_DATA)
            .situacao(UPDATED_SITUACAO);
        return historicoMaquina;
    }

    @BeforeEach
    public void initTest() {
        historicoMaquina = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistoricoMaquina() throws Exception {
        int databaseSizeBeforeCreate = historicoMaquinaRepository.findAll().size();

        // Create the HistoricoMaquina
        HistoricoMaquinaDTO historicoMaquinaDTO = historicoMaquinaMapper.toDto(historicoMaquina);
        restHistoricoMaquinaMockMvc.perform(post("/api/historico-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoMaquinaDTO)))
            .andExpect(status().isCreated());

        // Validate the HistoricoMaquina in the database
        List<HistoricoMaquina> historicoMaquinaList = historicoMaquinaRepository.findAll();
        assertThat(historicoMaquinaList).hasSize(databaseSizeBeforeCreate + 1);
        HistoricoMaquina testHistoricoMaquina = historicoMaquinaList.get(historicoMaquinaList.size() - 1);
        assertThat(testHistoricoMaquina.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testHistoricoMaquina.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testHistoricoMaquina.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
    }

    @Test
    @Transactional
    public void createHistoricoMaquinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historicoMaquinaRepository.findAll().size();

        // Create the HistoricoMaquina with an existing ID
        historicoMaquina.setId(1L);
        HistoricoMaquinaDTO historicoMaquinaDTO = historicoMaquinaMapper.toDto(historicoMaquina);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoricoMaquinaMockMvc.perform(post("/api/historico-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoMaquinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistoricoMaquina in the database
        List<HistoricoMaquina> historicoMaquinaList = historicoMaquinaRepository.findAll();
        assertThat(historicoMaquinaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHistoricoMaquinas() throws Exception {
        // Initialize the database
        historicoMaquinaRepository.saveAndFlush(historicoMaquina);

        // Get all the historicoMaquinaList
        restHistoricoMaquinaMockMvc.perform(get("/api/historico-maquinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historicoMaquina.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.toString())));
    }
    
    @Test
    @Transactional
    public void getHistoricoMaquina() throws Exception {
        // Initialize the database
        historicoMaquinaRepository.saveAndFlush(historicoMaquina);

        // Get the historicoMaquina
        restHistoricoMaquinaMockMvc.perform(get("/api/historico-maquinas/{id}", historicoMaquina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(historicoMaquina.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHistoricoMaquina() throws Exception {
        // Get the historicoMaquina
        restHistoricoMaquinaMockMvc.perform(get("/api/historico-maquinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistoricoMaquina() throws Exception {
        // Initialize the database
        historicoMaquinaRepository.saveAndFlush(historicoMaquina);

        int databaseSizeBeforeUpdate = historicoMaquinaRepository.findAll().size();

        // Update the historicoMaquina
        HistoricoMaquina updatedHistoricoMaquina = historicoMaquinaRepository.findById(historicoMaquina.getId()).get();
        // Disconnect from session so that the updates on updatedHistoricoMaquina are not directly saved in db
        em.detach(updatedHistoricoMaquina);
        updatedHistoricoMaquina
            .nome(UPDATED_NOME)
            .data(UPDATED_DATA)
            .situacao(UPDATED_SITUACAO);
        HistoricoMaquinaDTO historicoMaquinaDTO = historicoMaquinaMapper.toDto(updatedHistoricoMaquina);

        restHistoricoMaquinaMockMvc.perform(put("/api/historico-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoMaquinaDTO)))
            .andExpect(status().isOk());

        // Validate the HistoricoMaquina in the database
        List<HistoricoMaquina> historicoMaquinaList = historicoMaquinaRepository.findAll();
        assertThat(historicoMaquinaList).hasSize(databaseSizeBeforeUpdate);
        HistoricoMaquina testHistoricoMaquina = historicoMaquinaList.get(historicoMaquinaList.size() - 1);
        assertThat(testHistoricoMaquina.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testHistoricoMaquina.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testHistoricoMaquina.getSituacao()).isEqualTo(UPDATED_SITUACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingHistoricoMaquina() throws Exception {
        int databaseSizeBeforeUpdate = historicoMaquinaRepository.findAll().size();

        // Create the HistoricoMaquina
        HistoricoMaquinaDTO historicoMaquinaDTO = historicoMaquinaMapper.toDto(historicoMaquina);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoricoMaquinaMockMvc.perform(put("/api/historico-maquinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoMaquinaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistoricoMaquina in the database
        List<HistoricoMaquina> historicoMaquinaList = historicoMaquinaRepository.findAll();
        assertThat(historicoMaquinaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistoricoMaquina() throws Exception {
        // Initialize the database
        historicoMaquinaRepository.saveAndFlush(historicoMaquina);

        int databaseSizeBeforeDelete = historicoMaquinaRepository.findAll().size();

        // Delete the historicoMaquina
        restHistoricoMaquinaMockMvc.perform(delete("/api/historico-maquinas/{id}", historicoMaquina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistoricoMaquina> historicoMaquinaList = historicoMaquinaRepository.findAll();
        assertThat(historicoMaquinaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

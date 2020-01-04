package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.HistoricoOperadorCaixa;
import br.com.repository.HistoricoOperadorCaixaRepository;
import br.com.service.HistoricoOperadorCaixaService;
import br.com.service.dto.HistoricoOperadorCaixaDTO;
import br.com.service.mapper.HistoricoOperadorCaixaMapper;
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

import br.com.domain.enumeration.SituacaoOperadorCaixa;
/**
 * Integration tests for the {@link HistoricoOperadorCaixaResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class HistoricoOperadorCaixaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Float DEFAULT_CPF = 1F;
    private static final Float UPDATED_CPF = 2F;

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final SituacaoOperadorCaixa DEFAULT_SITUACAO = SituacaoOperadorCaixa.ATIVO;
    private static final SituacaoOperadorCaixa UPDATED_SITUACAO = SituacaoOperadorCaixa.INATIVO;

    @Autowired
    private HistoricoOperadorCaixaRepository historicoOperadorCaixaRepository;

    @Autowired
    private HistoricoOperadorCaixaMapper historicoOperadorCaixaMapper;

    @Autowired
    private HistoricoOperadorCaixaService historicoOperadorCaixaService;

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

    private MockMvc restHistoricoOperadorCaixaMockMvc;

    private HistoricoOperadorCaixa historicoOperadorCaixa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HistoricoOperadorCaixaResource historicoOperadorCaixaResource = new HistoricoOperadorCaixaResource(historicoOperadorCaixaService);
        this.restHistoricoOperadorCaixaMockMvc = MockMvcBuilders.standaloneSetup(historicoOperadorCaixaResource)
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
    public static HistoricoOperadorCaixa createEntity(EntityManager em) {
        HistoricoOperadorCaixa historicoOperadorCaixa = new HistoricoOperadorCaixa()
            .nome(DEFAULT_NOME)
            .cpf(DEFAULT_CPF)
            .data(DEFAULT_DATA)
            .situacao(DEFAULT_SITUACAO);
        return historicoOperadorCaixa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static HistoricoOperadorCaixa createUpdatedEntity(EntityManager em) {
        HistoricoOperadorCaixa historicoOperadorCaixa = new HistoricoOperadorCaixa()
            .nome(UPDATED_NOME)
            .cpf(UPDATED_CPF)
            .data(UPDATED_DATA)
            .situacao(UPDATED_SITUACAO);
        return historicoOperadorCaixa;
    }

    @BeforeEach
    public void initTest() {
        historicoOperadorCaixa = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistoricoOperadorCaixa() throws Exception {
        int databaseSizeBeforeCreate = historicoOperadorCaixaRepository.findAll().size();

        // Create the HistoricoOperadorCaixa
        HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO = historicoOperadorCaixaMapper.toDto(historicoOperadorCaixa);
        restHistoricoOperadorCaixaMockMvc.perform(post("/api/historico-operador-caixas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoOperadorCaixaDTO)))
            .andExpect(status().isCreated());

        // Validate the HistoricoOperadorCaixa in the database
        List<HistoricoOperadorCaixa> historicoOperadorCaixaList = historicoOperadorCaixaRepository.findAll();
        assertThat(historicoOperadorCaixaList).hasSize(databaseSizeBeforeCreate + 1);
        HistoricoOperadorCaixa testHistoricoOperadorCaixa = historicoOperadorCaixaList.get(historicoOperadorCaixaList.size() - 1);
        assertThat(testHistoricoOperadorCaixa.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testHistoricoOperadorCaixa.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testHistoricoOperadorCaixa.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testHistoricoOperadorCaixa.getSituacao()).isEqualTo(DEFAULT_SITUACAO);
    }

    @Test
    @Transactional
    public void createHistoricoOperadorCaixaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = historicoOperadorCaixaRepository.findAll().size();

        // Create the HistoricoOperadorCaixa with an existing ID
        historicoOperadorCaixa.setId(1L);
        HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO = historicoOperadorCaixaMapper.toDto(historicoOperadorCaixa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistoricoOperadorCaixaMockMvc.perform(post("/api/historico-operador-caixas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoOperadorCaixaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistoricoOperadorCaixa in the database
        List<HistoricoOperadorCaixa> historicoOperadorCaixaList = historicoOperadorCaixaRepository.findAll();
        assertThat(historicoOperadorCaixaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllHistoricoOperadorCaixas() throws Exception {
        // Initialize the database
        historicoOperadorCaixaRepository.saveAndFlush(historicoOperadorCaixa);

        // Get all the historicoOperadorCaixaList
        restHistoricoOperadorCaixaMockMvc.perform(get("/api/historico-operador-caixas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(historicoOperadorCaixa.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF.doubleValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].situacao").value(hasItem(DEFAULT_SITUACAO.toString())));
    }
    
    @Test
    @Transactional
    public void getHistoricoOperadorCaixa() throws Exception {
        // Initialize the database
        historicoOperadorCaixaRepository.saveAndFlush(historicoOperadorCaixa);

        // Get the historicoOperadorCaixa
        restHistoricoOperadorCaixaMockMvc.perform(get("/api/historico-operador-caixas/{id}", historicoOperadorCaixa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(historicoOperadorCaixa.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF.doubleValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.situacao").value(DEFAULT_SITUACAO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHistoricoOperadorCaixa() throws Exception {
        // Get the historicoOperadorCaixa
        restHistoricoOperadorCaixaMockMvc.perform(get("/api/historico-operador-caixas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistoricoOperadorCaixa() throws Exception {
        // Initialize the database
        historicoOperadorCaixaRepository.saveAndFlush(historicoOperadorCaixa);

        int databaseSizeBeforeUpdate = historicoOperadorCaixaRepository.findAll().size();

        // Update the historicoOperadorCaixa
        HistoricoOperadorCaixa updatedHistoricoOperadorCaixa = historicoOperadorCaixaRepository.findById(historicoOperadorCaixa.getId()).get();
        // Disconnect from session so that the updates on updatedHistoricoOperadorCaixa are not directly saved in db
        em.detach(updatedHistoricoOperadorCaixa);
        updatedHistoricoOperadorCaixa
            .nome(UPDATED_NOME)
            .cpf(UPDATED_CPF)
            .data(UPDATED_DATA)
            .situacao(UPDATED_SITUACAO);
        HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO = historicoOperadorCaixaMapper.toDto(updatedHistoricoOperadorCaixa);

        restHistoricoOperadorCaixaMockMvc.perform(put("/api/historico-operador-caixas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoOperadorCaixaDTO)))
            .andExpect(status().isOk());

        // Validate the HistoricoOperadorCaixa in the database
        List<HistoricoOperadorCaixa> historicoOperadorCaixaList = historicoOperadorCaixaRepository.findAll();
        assertThat(historicoOperadorCaixaList).hasSize(databaseSizeBeforeUpdate);
        HistoricoOperadorCaixa testHistoricoOperadorCaixa = historicoOperadorCaixaList.get(historicoOperadorCaixaList.size() - 1);
        assertThat(testHistoricoOperadorCaixa.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testHistoricoOperadorCaixa.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testHistoricoOperadorCaixa.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testHistoricoOperadorCaixa.getSituacao()).isEqualTo(UPDATED_SITUACAO);
    }

    @Test
    @Transactional
    public void updateNonExistingHistoricoOperadorCaixa() throws Exception {
        int databaseSizeBeforeUpdate = historicoOperadorCaixaRepository.findAll().size();

        // Create the HistoricoOperadorCaixa
        HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO = historicoOperadorCaixaMapper.toDto(historicoOperadorCaixa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistoricoOperadorCaixaMockMvc.perform(put("/api/historico-operador-caixas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(historicoOperadorCaixaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistoricoOperadorCaixa in the database
        List<HistoricoOperadorCaixa> historicoOperadorCaixaList = historicoOperadorCaixaRepository.findAll();
        assertThat(historicoOperadorCaixaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistoricoOperadorCaixa() throws Exception {
        // Initialize the database
        historicoOperadorCaixaRepository.saveAndFlush(historicoOperadorCaixa);

        int databaseSizeBeforeDelete = historicoOperadorCaixaRepository.findAll().size();

        // Delete the historicoOperadorCaixa
        restHistoricoOperadorCaixaMockMvc.perform(delete("/api/historico-operador-caixas/{id}", historicoOperadorCaixa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<HistoricoOperadorCaixa> historicoOperadorCaixaList = historicoOperadorCaixaRepository.findAll();
        assertThat(historicoOperadorCaixaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

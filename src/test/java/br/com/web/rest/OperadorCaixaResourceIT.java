package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.OperadorCaixa;
import br.com.repository.OperadorCaixaRepository;
import br.com.service.OperadorCaixaService;
import br.com.service.dto.OperadorCaixaDTO;
import br.com.service.mapper.OperadorCaixaMapper;
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
 * Integration tests for the {@link OperadorCaixaResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class OperadorCaixaResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Float DEFAULT_CPF = 1F;
    private static final Float UPDATED_CPF = 2F;

    private static final Long DEFAULT_ID_LOJA = 1L;
    private static final Long UPDATED_ID_LOJA = 2L;

    @Autowired
    private OperadorCaixaRepository operadorCaixaRepository;

    @Autowired
    private OperadorCaixaMapper operadorCaixaMapper;

    @Autowired
    private OperadorCaixaService operadorCaixaService;

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

    private MockMvc restOperadorCaixaMockMvc;

    private OperadorCaixa operadorCaixa;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OperadorCaixaResource operadorCaixaResource = new OperadorCaixaResource(operadorCaixaService);
        this.restOperadorCaixaMockMvc = MockMvcBuilders.standaloneSetup(operadorCaixaResource)
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
    public static OperadorCaixa createEntity(EntityManager em) {
        OperadorCaixa operadorCaixa = new OperadorCaixa()
            .nome(DEFAULT_NOME)
            .cpf(DEFAULT_CPF)
            .idLoja(DEFAULT_ID_LOJA);
        return operadorCaixa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OperadorCaixa createUpdatedEntity(EntityManager em) {
        OperadorCaixa operadorCaixa = new OperadorCaixa()
            .nome(UPDATED_NOME)
            .cpf(UPDATED_CPF)
            .idLoja(UPDATED_ID_LOJA);
        return operadorCaixa;
    }

    @BeforeEach
    public void initTest() {
        operadorCaixa = createEntity(em);
    }

    @Test
    @Transactional
    public void createOperadorCaixa() throws Exception {
        int databaseSizeBeforeCreate = operadorCaixaRepository.findAll().size();

        // Create the OperadorCaixa
        OperadorCaixaDTO operadorCaixaDTO = operadorCaixaMapper.toDto(operadorCaixa);
        restOperadorCaixaMockMvc.perform(post("/api/operador-caixas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operadorCaixaDTO)))
            .andExpect(status().isCreated());

        // Validate the OperadorCaixa in the database
        List<OperadorCaixa> operadorCaixaList = operadorCaixaRepository.findAll();
        assertThat(operadorCaixaList).hasSize(databaseSizeBeforeCreate + 1);
        OperadorCaixa testOperadorCaixa = operadorCaixaList.get(operadorCaixaList.size() - 1);
        assertThat(testOperadorCaixa.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testOperadorCaixa.getCpf()).isEqualTo(DEFAULT_CPF);
        assertThat(testOperadorCaixa.getIdLoja()).isEqualTo(DEFAULT_ID_LOJA);
    }

    @Test
    @Transactional
    public void createOperadorCaixaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = operadorCaixaRepository.findAll().size();

        // Create the OperadorCaixa with an existing ID
        operadorCaixa.setId(1L);
        OperadorCaixaDTO operadorCaixaDTO = operadorCaixaMapper.toDto(operadorCaixa);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOperadorCaixaMockMvc.perform(post("/api/operador-caixas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operadorCaixaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperadorCaixa in the database
        List<OperadorCaixa> operadorCaixaList = operadorCaixaRepository.findAll();
        assertThat(operadorCaixaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllOperadorCaixas() throws Exception {
        // Initialize the database
        operadorCaixaRepository.saveAndFlush(operadorCaixa);

        // Get all the operadorCaixaList
        restOperadorCaixaMockMvc.perform(get("/api/operador-caixas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(operadorCaixa.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF.doubleValue())))
            .andExpect(jsonPath("$.[*].idLoja").value(hasItem(DEFAULT_ID_LOJA.intValue())));
    }
    
    @Test
    @Transactional
    public void getOperadorCaixa() throws Exception {
        // Initialize the database
        operadorCaixaRepository.saveAndFlush(operadorCaixa);

        // Get the operadorCaixa
        restOperadorCaixaMockMvc.perform(get("/api/operador-caixas/{id}", operadorCaixa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(operadorCaixa.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF.doubleValue()))
            .andExpect(jsonPath("$.idLoja").value(DEFAULT_ID_LOJA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOperadorCaixa() throws Exception {
        // Get the operadorCaixa
        restOperadorCaixaMockMvc.perform(get("/api/operador-caixas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOperadorCaixa() throws Exception {
        // Initialize the database
        operadorCaixaRepository.saveAndFlush(operadorCaixa);

        int databaseSizeBeforeUpdate = operadorCaixaRepository.findAll().size();

        // Update the operadorCaixa
        OperadorCaixa updatedOperadorCaixa = operadorCaixaRepository.findById(operadorCaixa.getId()).get();
        // Disconnect from session so that the updates on updatedOperadorCaixa are not directly saved in db
        em.detach(updatedOperadorCaixa);
        updatedOperadorCaixa
            .nome(UPDATED_NOME)
            .cpf(UPDATED_CPF)
            .idLoja(UPDATED_ID_LOJA);
        OperadorCaixaDTO operadorCaixaDTO = operadorCaixaMapper.toDto(updatedOperadorCaixa);

        restOperadorCaixaMockMvc.perform(put("/api/operador-caixas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operadorCaixaDTO)))
            .andExpect(status().isOk());

        // Validate the OperadorCaixa in the database
        List<OperadorCaixa> operadorCaixaList = operadorCaixaRepository.findAll();
        assertThat(operadorCaixaList).hasSize(databaseSizeBeforeUpdate);
        OperadorCaixa testOperadorCaixa = operadorCaixaList.get(operadorCaixaList.size() - 1);
        assertThat(testOperadorCaixa.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testOperadorCaixa.getCpf()).isEqualTo(UPDATED_CPF);
        assertThat(testOperadorCaixa.getIdLoja()).isEqualTo(UPDATED_ID_LOJA);
    }

    @Test
    @Transactional
    public void updateNonExistingOperadorCaixa() throws Exception {
        int databaseSizeBeforeUpdate = operadorCaixaRepository.findAll().size();

        // Create the OperadorCaixa
        OperadorCaixaDTO operadorCaixaDTO = operadorCaixaMapper.toDto(operadorCaixa);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOperadorCaixaMockMvc.perform(put("/api/operador-caixas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(operadorCaixaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OperadorCaixa in the database
        List<OperadorCaixa> operadorCaixaList = operadorCaixaRepository.findAll();
        assertThat(operadorCaixaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOperadorCaixa() throws Exception {
        // Initialize the database
        operadorCaixaRepository.saveAndFlush(operadorCaixa);

        int databaseSizeBeforeDelete = operadorCaixaRepository.findAll().size();

        // Delete the operadorCaixa
        restOperadorCaixaMockMvc.perform(delete("/api/operador-caixas/{id}", operadorCaixa.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OperadorCaixa> operadorCaixaList = operadorCaixaRepository.findAll();
        assertThat(operadorCaixaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

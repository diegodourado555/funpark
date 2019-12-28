package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.ContaCorrente;
import br.com.repository.ContaCorrenteRepository;
import br.com.service.ContaCorrenteService;
import br.com.service.dto.ContaCorrenteDTO;
import br.com.service.mapper.ContaCorrenteMapper;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static br.com.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import br.com.domain.enumeration.MetodoPagamento;
/**
 * Integration tests for the {@link ContaCorrenteResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class ContaCorrenteResourceIT {

    private static final Double DEFAULT_VALOR = 1D;
    private static final Double UPDATED_VALOR = 2D;

    private static final Instant DEFAULT_DATA = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_DATA = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final MetodoPagamento DEFAULT_METODO_PAGAMENTO = MetodoPagamento.DINHEIRO;
    private static final MetodoPagamento UPDATED_METODO_PAGAMENTO = MetodoPagamento.CARTAO;

    @Autowired
    private ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    private ContaCorrenteMapper contaCorrenteMapper;

    @Autowired
    private ContaCorrenteService contaCorrenteService;

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

    private MockMvc restContaCorrenteMockMvc;

    private ContaCorrente contaCorrente;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ContaCorrenteResource contaCorrenteResource = new ContaCorrenteResource(contaCorrenteService);
        this.restContaCorrenteMockMvc = MockMvcBuilders.standaloneSetup(contaCorrenteResource)
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
    public static ContaCorrente createEntity(EntityManager em) {
        ContaCorrente contaCorrente = new ContaCorrente()
            .valor(DEFAULT_VALOR)
            .data(DEFAULT_DATA)
            .metodoPagamento(DEFAULT_METODO_PAGAMENTO);
        return contaCorrente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ContaCorrente createUpdatedEntity(EntityManager em) {
        ContaCorrente contaCorrente = new ContaCorrente()
            .valor(UPDATED_VALOR)
            .data(UPDATED_DATA)
            .metodoPagamento(UPDATED_METODO_PAGAMENTO);
        return contaCorrente;
    }

    @BeforeEach
    public void initTest() {
        contaCorrente = createEntity(em);
    }

    @Test
    @Transactional
    public void createContaCorrente() throws Exception {
        int databaseSizeBeforeCreate = contaCorrenteRepository.findAll().size();

        // Create the ContaCorrente
        ContaCorrenteDTO contaCorrenteDTO = contaCorrenteMapper.toDto(contaCorrente);
        restContaCorrenteMockMvc.perform(post("/api/conta-correntes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contaCorrenteDTO)))
            .andExpect(status().isCreated());

        // Validate the ContaCorrente in the database
        List<ContaCorrente> contaCorrenteList = contaCorrenteRepository.findAll();
        assertThat(contaCorrenteList).hasSize(databaseSizeBeforeCreate + 1);
        ContaCorrente testContaCorrente = contaCorrenteList.get(contaCorrenteList.size() - 1);
        assertThat(testContaCorrente.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testContaCorrente.getData()).isEqualTo(DEFAULT_DATA);
        assertThat(testContaCorrente.getMetodoPagamento()).isEqualTo(DEFAULT_METODO_PAGAMENTO);
    }

    @Test
    @Transactional
    public void createContaCorrenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = contaCorrenteRepository.findAll().size();

        // Create the ContaCorrente with an existing ID
        contaCorrente.setId(1L);
        ContaCorrenteDTO contaCorrenteDTO = contaCorrenteMapper.toDto(contaCorrente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restContaCorrenteMockMvc.perform(post("/api/conta-correntes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contaCorrenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContaCorrente in the database
        List<ContaCorrente> contaCorrenteList = contaCorrenteRepository.findAll();
        assertThat(contaCorrenteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllContaCorrentes() throws Exception {
        // Initialize the database
        contaCorrenteRepository.saveAndFlush(contaCorrente);

        // Get all the contaCorrenteList
        restContaCorrenteMockMvc.perform(get("/api/conta-correntes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(contaCorrente.getId().intValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.doubleValue())))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].metodoPagamento").value(hasItem(DEFAULT_METODO_PAGAMENTO.toString())));
    }
    
    @Test
    @Transactional
    public void getContaCorrente() throws Exception {
        // Initialize the database
        contaCorrenteRepository.saveAndFlush(contaCorrente);

        // Get the contaCorrente
        restContaCorrenteMockMvc.perform(get("/api/conta-correntes/{id}", contaCorrente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(contaCorrente.getId().intValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.doubleValue()))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.metodoPagamento").value(DEFAULT_METODO_PAGAMENTO.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingContaCorrente() throws Exception {
        // Get the contaCorrente
        restContaCorrenteMockMvc.perform(get("/api/conta-correntes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateContaCorrente() throws Exception {
        // Initialize the database
        contaCorrenteRepository.saveAndFlush(contaCorrente);

        int databaseSizeBeforeUpdate = contaCorrenteRepository.findAll().size();

        // Update the contaCorrente
        ContaCorrente updatedContaCorrente = contaCorrenteRepository.findById(contaCorrente.getId()).get();
        // Disconnect from session so that the updates on updatedContaCorrente are not directly saved in db
        em.detach(updatedContaCorrente);
        updatedContaCorrente
            .valor(UPDATED_VALOR)
            .data(UPDATED_DATA)
            .metodoPagamento(UPDATED_METODO_PAGAMENTO);
        ContaCorrenteDTO contaCorrenteDTO = contaCorrenteMapper.toDto(updatedContaCorrente);

        restContaCorrenteMockMvc.perform(put("/api/conta-correntes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contaCorrenteDTO)))
            .andExpect(status().isOk());

        // Validate the ContaCorrente in the database
        List<ContaCorrente> contaCorrenteList = contaCorrenteRepository.findAll();
        assertThat(contaCorrenteList).hasSize(databaseSizeBeforeUpdate);
        ContaCorrente testContaCorrente = contaCorrenteList.get(contaCorrenteList.size() - 1);
        assertThat(testContaCorrente.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testContaCorrente.getData()).isEqualTo(UPDATED_DATA);
        assertThat(testContaCorrente.getMetodoPagamento()).isEqualTo(UPDATED_METODO_PAGAMENTO);
    }

    @Test
    @Transactional
    public void updateNonExistingContaCorrente() throws Exception {
        int databaseSizeBeforeUpdate = contaCorrenteRepository.findAll().size();

        // Create the ContaCorrente
        ContaCorrenteDTO contaCorrenteDTO = contaCorrenteMapper.toDto(contaCorrente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restContaCorrenteMockMvc.perform(put("/api/conta-correntes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(contaCorrenteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ContaCorrente in the database
        List<ContaCorrente> contaCorrenteList = contaCorrenteRepository.findAll();
        assertThat(contaCorrenteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteContaCorrente() throws Exception {
        // Initialize the database
        contaCorrenteRepository.saveAndFlush(contaCorrente);

        int databaseSizeBeforeDelete = contaCorrenteRepository.findAll().size();

        // Delete the contaCorrente
        restContaCorrenteMockMvc.perform(delete("/api/conta-correntes/{id}", contaCorrente.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ContaCorrente> contaCorrenteList = contaCorrenteRepository.findAll();
        assertThat(contaCorrenteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

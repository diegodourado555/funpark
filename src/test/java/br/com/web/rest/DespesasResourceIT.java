package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.Despesas;
import br.com.repository.DespesasRepository;
import br.com.service.DespesasService;
import br.com.service.dto.DespesasDTO;
import br.com.service.mapper.DespesasMapper;
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
 * Integration tests for the {@link DespesasResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class DespesasResourceIT {

    private static final String DEFAULT_CODIGO = "AAAAAAAAAA";
    private static final String UPDATED_CODIGO = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private DespesasRepository despesasRepository;

    @Autowired
    private DespesasMapper despesasMapper;

    @Autowired
    private DespesasService despesasService;

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

    private MockMvc restDespesasMockMvc;

    private Despesas despesas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DespesasResource despesasResource = new DespesasResource(despesasService);
        this.restDespesasMockMvc = MockMvcBuilders.standaloneSetup(despesasResource)
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
    public static Despesas createEntity(EntityManager em) {
        Despesas despesas = new Despesas()
            .codigo(DEFAULT_CODIGO)
            .descricao(DEFAULT_DESCRICAO);
        return despesas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Despesas createUpdatedEntity(EntityManager em) {
        Despesas despesas = new Despesas()
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO);
        return despesas;
    }

    @BeforeEach
    public void initTest() {
        despesas = createEntity(em);
    }

    @Test
    @Transactional
    public void createDespesas() throws Exception {
        int databaseSizeBeforeCreate = despesasRepository.findAll().size();

        // Create the Despesas
        DespesasDTO despesasDTO = despesasMapper.toDto(despesas);
        restDespesasMockMvc.perform(post("/api/despesas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(despesasDTO)))
            .andExpect(status().isCreated());

        // Validate the Despesas in the database
        List<Despesas> despesasList = despesasRepository.findAll();
        assertThat(despesasList).hasSize(databaseSizeBeforeCreate + 1);
        Despesas testDespesas = despesasList.get(despesasList.size() - 1);
        assertThat(testDespesas.getCodigo()).isEqualTo(DEFAULT_CODIGO);
        assertThat(testDespesas.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createDespesasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = despesasRepository.findAll().size();

        // Create the Despesas with an existing ID
        despesas.setId(1L);
        DespesasDTO despesasDTO = despesasMapper.toDto(despesas);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDespesasMockMvc.perform(post("/api/despesas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(despesasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Despesas in the database
        List<Despesas> despesasList = despesasRepository.findAll();
        assertThat(despesasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDespesas() throws Exception {
        // Initialize the database
        despesasRepository.saveAndFlush(despesas);

        // Get all the despesasList
        restDespesasMockMvc.perform(get("/api/despesas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(despesas.getId().intValue())))
            .andExpect(jsonPath("$.[*].codigo").value(hasItem(DEFAULT_CODIGO)))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getDespesas() throws Exception {
        // Initialize the database
        despesasRepository.saveAndFlush(despesas);

        // Get the despesas
        restDespesasMockMvc.perform(get("/api/despesas/{id}", despesas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(despesas.getId().intValue()))
            .andExpect(jsonPath("$.codigo").value(DEFAULT_CODIGO))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingDespesas() throws Exception {
        // Get the despesas
        restDespesasMockMvc.perform(get("/api/despesas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDespesas() throws Exception {
        // Initialize the database
        despesasRepository.saveAndFlush(despesas);

        int databaseSizeBeforeUpdate = despesasRepository.findAll().size();

        // Update the despesas
        Despesas updatedDespesas = despesasRepository.findById(despesas.getId()).get();
        // Disconnect from session so that the updates on updatedDespesas are not directly saved in db
        em.detach(updatedDespesas);
        updatedDespesas
            .codigo(UPDATED_CODIGO)
            .descricao(UPDATED_DESCRICAO);
        DespesasDTO despesasDTO = despesasMapper.toDto(updatedDespesas);

        restDespesasMockMvc.perform(put("/api/despesas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(despesasDTO)))
            .andExpect(status().isOk());

        // Validate the Despesas in the database
        List<Despesas> despesasList = despesasRepository.findAll();
        assertThat(despesasList).hasSize(databaseSizeBeforeUpdate);
        Despesas testDespesas = despesasList.get(despesasList.size() - 1);
        assertThat(testDespesas.getCodigo()).isEqualTo(UPDATED_CODIGO);
        assertThat(testDespesas.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingDespesas() throws Exception {
        int databaseSizeBeforeUpdate = despesasRepository.findAll().size();

        // Create the Despesas
        DespesasDTO despesasDTO = despesasMapper.toDto(despesas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDespesasMockMvc.perform(put("/api/despesas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(despesasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Despesas in the database
        List<Despesas> despesasList = despesasRepository.findAll();
        assertThat(despesasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDespesas() throws Exception {
        // Initialize the database
        despesasRepository.saveAndFlush(despesas);

        int databaseSizeBeforeDelete = despesasRepository.findAll().size();

        // Delete the despesas
        restDespesasMockMvc.perform(delete("/api/despesas/{id}", despesas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Despesas> despesasList = despesasRepository.findAll();
        assertThat(despesasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

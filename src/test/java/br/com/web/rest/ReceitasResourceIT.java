package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.Receitas;
import br.com.repository.ReceitasRepository;
import br.com.service.ReceitasService;
import br.com.service.dto.ReceitasDTO;
import br.com.service.mapper.ReceitasMapper;
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
 * Integration tests for the {@link ReceitasResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class ReceitasResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private ReceitasRepository receitasRepository;

    @Autowired
    private ReceitasMapper receitasMapper;

    @Autowired
    private ReceitasService receitasService;

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

    private MockMvc restReceitasMockMvc;

    private Receitas receitas;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReceitasResource receitasResource = new ReceitasResource(receitasService);
        this.restReceitasMockMvc = MockMvcBuilders.standaloneSetup(receitasResource)
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
    public static Receitas createEntity(EntityManager em) {
        Receitas receitas = new Receitas()
            .descricao(DEFAULT_DESCRICAO);
        return receitas;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Receitas createUpdatedEntity(EntityManager em) {
        Receitas receitas = new Receitas()
            .descricao(UPDATED_DESCRICAO);
        return receitas;
    }

    @BeforeEach
    public void initTest() {
        receitas = createEntity(em);
    }

    @Test
    @Transactional
    public void createReceitas() throws Exception {
        int databaseSizeBeforeCreate = receitasRepository.findAll().size();

        // Create the Receitas
        ReceitasDTO receitasDTO = receitasMapper.toDto(receitas);
        restReceitasMockMvc.perform(post("/api/receitas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receitasDTO)))
            .andExpect(status().isCreated());

        // Validate the Receitas in the database
        List<Receitas> receitasList = receitasRepository.findAll();
        assertThat(receitasList).hasSize(databaseSizeBeforeCreate + 1);
        Receitas testReceitas = receitasList.get(receitasList.size() - 1);
        assertThat(testReceitas.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createReceitasWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = receitasRepository.findAll().size();

        // Create the Receitas with an existing ID
        receitas.setId(1L);
        ReceitasDTO receitasDTO = receitasMapper.toDto(receitas);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReceitasMockMvc.perform(post("/api/receitas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receitasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Receitas in the database
        List<Receitas> receitasList = receitasRepository.findAll();
        assertThat(receitasList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllReceitas() throws Exception {
        // Initialize the database
        receitasRepository.saveAndFlush(receitas);

        // Get all the receitasList
        restReceitasMockMvc.perform(get("/api/receitas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(receitas.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getReceitas() throws Exception {
        // Initialize the database
        receitasRepository.saveAndFlush(receitas);

        // Get the receitas
        restReceitasMockMvc.perform(get("/api/receitas/{id}", receitas.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(receitas.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingReceitas() throws Exception {
        // Get the receitas
        restReceitasMockMvc.perform(get("/api/receitas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReceitas() throws Exception {
        // Initialize the database
        receitasRepository.saveAndFlush(receitas);

        int databaseSizeBeforeUpdate = receitasRepository.findAll().size();

        // Update the receitas
        Receitas updatedReceitas = receitasRepository.findById(receitas.getId()).get();
        // Disconnect from session so that the updates on updatedReceitas are not directly saved in db
        em.detach(updatedReceitas);
        updatedReceitas
            .descricao(UPDATED_DESCRICAO);
        ReceitasDTO receitasDTO = receitasMapper.toDto(updatedReceitas);

        restReceitasMockMvc.perform(put("/api/receitas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receitasDTO)))
            .andExpect(status().isOk());

        // Validate the Receitas in the database
        List<Receitas> receitasList = receitasRepository.findAll();
        assertThat(receitasList).hasSize(databaseSizeBeforeUpdate);
        Receitas testReceitas = receitasList.get(receitasList.size() - 1);
        assertThat(testReceitas.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingReceitas() throws Exception {
        int databaseSizeBeforeUpdate = receitasRepository.findAll().size();

        // Create the Receitas
        ReceitasDTO receitasDTO = receitasMapper.toDto(receitas);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReceitasMockMvc.perform(put("/api/receitas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(receitasDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Receitas in the database
        List<Receitas> receitasList = receitasRepository.findAll();
        assertThat(receitasList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReceitas() throws Exception {
        // Initialize the database
        receitasRepository.saveAndFlush(receitas);

        int databaseSizeBeforeDelete = receitasRepository.findAll().size();

        // Delete the receitas
        restReceitasMockMvc.perform(delete("/api/receitas/{id}", receitas.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Receitas> receitasList = receitasRepository.findAll();
        assertThat(receitasList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

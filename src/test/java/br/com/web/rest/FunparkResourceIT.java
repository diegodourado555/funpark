package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.Funpark;
import br.com.repository.FunparkRepository;
import br.com.service.FunparkService;
import br.com.service.dto.FunparkDTO;
import br.com.service.mapper.FunparkMapper;
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
 * Integration tests for the {@link FunparkResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class FunparkResourceIT {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    @Autowired
    private FunparkRepository funparkRepository;

    @Autowired
    private FunparkMapper funparkMapper;

    @Autowired
    private FunparkService funparkService;

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

    private MockMvc restFunparkMockMvc;

    private Funpark funpark;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final FunparkResource funparkResource = new FunparkResource(funparkService);
        this.restFunparkMockMvc = MockMvcBuilders.standaloneSetup(funparkResource)
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
    public static Funpark createEntity(EntityManager em) {
        Funpark funpark = new Funpark()
            .nome(DEFAULT_NOME);
        return funpark;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Funpark createUpdatedEntity(EntityManager em) {
        Funpark funpark = new Funpark()
            .nome(UPDATED_NOME);
        return funpark;
    }

    @BeforeEach
    public void initTest() {
        funpark = createEntity(em);
    }

    @Test
    @Transactional
    public void createFunpark() throws Exception {
        int databaseSizeBeforeCreate = funparkRepository.findAll().size();

        // Create the Funpark
        FunparkDTO funparkDTO = funparkMapper.toDto(funpark);
        restFunparkMockMvc.perform(post("/api/funparks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funparkDTO)))
            .andExpect(status().isCreated());

        // Validate the Funpark in the database
        List<Funpark> funparkList = funparkRepository.findAll();
        assertThat(funparkList).hasSize(databaseSizeBeforeCreate + 1);
        Funpark testFunpark = funparkList.get(funparkList.size() - 1);
        assertThat(testFunpark.getNome()).isEqualTo(DEFAULT_NOME);
    }

    @Test
    @Transactional
    public void createFunparkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = funparkRepository.findAll().size();

        // Create the Funpark with an existing ID
        funpark.setId(1L);
        FunparkDTO funparkDTO = funparkMapper.toDto(funpark);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFunparkMockMvc.perform(post("/api/funparks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funparkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Funpark in the database
        List<Funpark> funparkList = funparkRepository.findAll();
        assertThat(funparkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFunparks() throws Exception {
        // Initialize the database
        funparkRepository.saveAndFlush(funpark);

        // Get all the funparkList
        restFunparkMockMvc.perform(get("/api/funparks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(funpark.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME)));
    }
    
    @Test
    @Transactional
    public void getFunpark() throws Exception {
        // Initialize the database
        funparkRepository.saveAndFlush(funpark);

        // Get the funpark
        restFunparkMockMvc.perform(get("/api/funparks/{id}", funpark.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(funpark.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME));
    }

    @Test
    @Transactional
    public void getNonExistingFunpark() throws Exception {
        // Get the funpark
        restFunparkMockMvc.perform(get("/api/funparks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFunpark() throws Exception {
        // Initialize the database
        funparkRepository.saveAndFlush(funpark);

        int databaseSizeBeforeUpdate = funparkRepository.findAll().size();

        // Update the funpark
        Funpark updatedFunpark = funparkRepository.findById(funpark.getId()).get();
        // Disconnect from session so that the updates on updatedFunpark are not directly saved in db
        em.detach(updatedFunpark);
        updatedFunpark
            .nome(UPDATED_NOME);
        FunparkDTO funparkDTO = funparkMapper.toDto(updatedFunpark);

        restFunparkMockMvc.perform(put("/api/funparks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funparkDTO)))
            .andExpect(status().isOk());

        // Validate the Funpark in the database
        List<Funpark> funparkList = funparkRepository.findAll();
        assertThat(funparkList).hasSize(databaseSizeBeforeUpdate);
        Funpark testFunpark = funparkList.get(funparkList.size() - 1);
        assertThat(testFunpark.getNome()).isEqualTo(UPDATED_NOME);
    }

    @Test
    @Transactional
    public void updateNonExistingFunpark() throws Exception {
        int databaseSizeBeforeUpdate = funparkRepository.findAll().size();

        // Create the Funpark
        FunparkDTO funparkDTO = funparkMapper.toDto(funpark);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFunparkMockMvc.perform(put("/api/funparks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(funparkDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Funpark in the database
        List<Funpark> funparkList = funparkRepository.findAll();
        assertThat(funparkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFunpark() throws Exception {
        // Initialize the database
        funparkRepository.saveAndFlush(funpark);

        int databaseSizeBeforeDelete = funparkRepository.findAll().size();

        // Delete the funpark
        restFunparkMockMvc.perform(delete("/api/funparks/{id}", funpark.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Funpark> funparkList = funparkRepository.findAll();
        assertThat(funparkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

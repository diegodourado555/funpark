package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.Loja;
import br.com.repository.LojaRepository;
import br.com.service.LojaService;
import br.com.service.dto.LojaDTO;
import br.com.service.mapper.LojaMapper;
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
 * Integration tests for the {@link LojaResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class LojaResourceIT {

    private static final String DEFAULT_NOME_FANTASIA = "AAAAAAAAAA";
    private static final String UPDATED_NOME_FANTASIA = "BBBBBBBBBB";

    private static final String DEFAULT_RAZAO_SOCIAL = "AAAAAAAAAA";
    private static final String UPDATED_RAZAO_SOCIAL = "BBBBBBBBBB";

    private static final String DEFAULT_C_NPJ = "AAAAAAAAAA";
    private static final String UPDATED_C_NPJ = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    @Autowired
    private LojaRepository lojaRepository;

    @Autowired
    private LojaMapper lojaMapper;

    @Autowired
    private LojaService lojaService;

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

    private MockMvc restLojaMockMvc;

    private Loja loja;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LojaResource lojaResource = new LojaResource(lojaService);
        this.restLojaMockMvc = MockMvcBuilders.standaloneSetup(lojaResource)
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
    public static Loja createEntity(EntityManager em) {
        Loja loja = new Loja()
            .nomeFantasia(DEFAULT_NOME_FANTASIA)
            .razaoSocial(DEFAULT_RAZAO_SOCIAL)
            .cNPJ(DEFAULT_C_NPJ)
            .endereco(DEFAULT_ENDERECO);
        return loja;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Loja createUpdatedEntity(EntityManager em) {
        Loja loja = new Loja()
            .nomeFantasia(UPDATED_NOME_FANTASIA)
            .razaoSocial(UPDATED_RAZAO_SOCIAL)
            .cNPJ(UPDATED_C_NPJ)
            .endereco(UPDATED_ENDERECO);
        return loja;
    }

    @BeforeEach
    public void initTest() {
        loja = createEntity(em);
    }

    @Test
    @Transactional
    public void createLoja() throws Exception {
        int databaseSizeBeforeCreate = lojaRepository.findAll().size();

        // Create the Loja
        LojaDTO lojaDTO = lojaMapper.toDto(loja);
        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lojaDTO)))
            .andExpect(status().isCreated());

        // Validate the Loja in the database
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeCreate + 1);
        Loja testLoja = lojaList.get(lojaList.size() - 1);
        assertThat(testLoja.getNomeFantasia()).isEqualTo(DEFAULT_NOME_FANTASIA);
        assertThat(testLoja.getRazaoSocial()).isEqualTo(DEFAULT_RAZAO_SOCIAL);
        assertThat(testLoja.getcNPJ()).isEqualTo(DEFAULT_C_NPJ);
        assertThat(testLoja.getEndereco()).isEqualTo(DEFAULT_ENDERECO);
    }

    @Test
    @Transactional
    public void createLojaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = lojaRepository.findAll().size();

        // Create the Loja with an existing ID
        loja.setId(1L);
        LojaDTO lojaDTO = lojaMapper.toDto(loja);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLojaMockMvc.perform(post("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lojaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Loja in the database
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLojas() throws Exception {
        // Initialize the database
        lojaRepository.saveAndFlush(loja);

        // Get all the lojaList
        restLojaMockMvc.perform(get("/api/lojas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(loja.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeFantasia").value(hasItem(DEFAULT_NOME_FANTASIA)))
            .andExpect(jsonPath("$.[*].razaoSocial").value(hasItem(DEFAULT_RAZAO_SOCIAL)))
            .andExpect(jsonPath("$.[*].cNPJ").value(hasItem(DEFAULT_C_NPJ)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)));
    }
    
    @Test
    @Transactional
    public void getLoja() throws Exception {
        // Initialize the database
        lojaRepository.saveAndFlush(loja);

        // Get the loja
        restLojaMockMvc.perform(get("/api/lojas/{id}", loja.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(loja.getId().intValue()))
            .andExpect(jsonPath("$.nomeFantasia").value(DEFAULT_NOME_FANTASIA))
            .andExpect(jsonPath("$.razaoSocial").value(DEFAULT_RAZAO_SOCIAL))
            .andExpect(jsonPath("$.cNPJ").value(DEFAULT_C_NPJ))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO));
    }

    @Test
    @Transactional
    public void getNonExistingLoja() throws Exception {
        // Get the loja
        restLojaMockMvc.perform(get("/api/lojas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLoja() throws Exception {
        // Initialize the database
        lojaRepository.saveAndFlush(loja);

        int databaseSizeBeforeUpdate = lojaRepository.findAll().size();

        // Update the loja
        Loja updatedLoja = lojaRepository.findById(loja.getId()).get();
        // Disconnect from session so that the updates on updatedLoja are not directly saved in db
        em.detach(updatedLoja);
        updatedLoja
            .nomeFantasia(UPDATED_NOME_FANTASIA)
            .razaoSocial(UPDATED_RAZAO_SOCIAL)
            .cNPJ(UPDATED_C_NPJ)
            .endereco(UPDATED_ENDERECO);
        LojaDTO lojaDTO = lojaMapper.toDto(updatedLoja);

        restLojaMockMvc.perform(put("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lojaDTO)))
            .andExpect(status().isOk());

        // Validate the Loja in the database
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeUpdate);
        Loja testLoja = lojaList.get(lojaList.size() - 1);
        assertThat(testLoja.getNomeFantasia()).isEqualTo(UPDATED_NOME_FANTASIA);
        assertThat(testLoja.getRazaoSocial()).isEqualTo(UPDATED_RAZAO_SOCIAL);
        assertThat(testLoja.getcNPJ()).isEqualTo(UPDATED_C_NPJ);
        assertThat(testLoja.getEndereco()).isEqualTo(UPDATED_ENDERECO);
    }

    @Test
    @Transactional
    public void updateNonExistingLoja() throws Exception {
        int databaseSizeBeforeUpdate = lojaRepository.findAll().size();

        // Create the Loja
        LojaDTO lojaDTO = lojaMapper.toDto(loja);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLojaMockMvc.perform(put("/api/lojas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lojaDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Loja in the database
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLoja() throws Exception {
        // Initialize the database
        lojaRepository.saveAndFlush(loja);

        int databaseSizeBeforeDelete = lojaRepository.findAll().size();

        // Delete the loja
        restLojaMockMvc.perform(delete("/api/lojas/{id}", loja.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Loja> lojaList = lojaRepository.findAll();
        assertThat(lojaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

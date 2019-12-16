package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.PerfilAcesso;
import br.com.repository.PerfilAcessoRepository;
import br.com.service.PerfilAcessoService;
import br.com.service.dto.PerfilAcessoDTO;
import br.com.service.mapper.PerfilAcessoMapper;
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
 * Integration tests for the {@link PerfilAcessoResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class PerfilAcessoResourceIT {

    private static final String DEFAULT_DESCRICAO = "AAAAAAAAAA";
    private static final String UPDATED_DESCRICAO = "BBBBBBBBBB";

    @Autowired
    private PerfilAcessoRepository perfilAcessoRepository;

    @Autowired
    private PerfilAcessoMapper perfilAcessoMapper;

    @Autowired
    private PerfilAcessoService perfilAcessoService;

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

    private MockMvc restPerfilAcessoMockMvc;

    private PerfilAcesso perfilAcesso;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PerfilAcessoResource perfilAcessoResource = new PerfilAcessoResource(perfilAcessoService);
        this.restPerfilAcessoMockMvc = MockMvcBuilders.standaloneSetup(perfilAcessoResource)
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
    public static PerfilAcesso createEntity(EntityManager em) {
        PerfilAcesso perfilAcesso = new PerfilAcesso()
            .descricao(DEFAULT_DESCRICAO);
        return perfilAcesso;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PerfilAcesso createUpdatedEntity(EntityManager em) {
        PerfilAcesso perfilAcesso = new PerfilAcesso()
            .descricao(UPDATED_DESCRICAO);
        return perfilAcesso;
    }

    @BeforeEach
    public void initTest() {
        perfilAcesso = createEntity(em);
    }

    @Test
    @Transactional
    public void createPerfilAcesso() throws Exception {
        int databaseSizeBeforeCreate = perfilAcessoRepository.findAll().size();

        // Create the PerfilAcesso
        PerfilAcessoDTO perfilAcessoDTO = perfilAcessoMapper.toDto(perfilAcesso);
        restPerfilAcessoMockMvc.perform(post("/api/perfil-acessos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilAcessoDTO)))
            .andExpect(status().isCreated());

        // Validate the PerfilAcesso in the database
        List<PerfilAcesso> perfilAcessoList = perfilAcessoRepository.findAll();
        assertThat(perfilAcessoList).hasSize(databaseSizeBeforeCreate + 1);
        PerfilAcesso testPerfilAcesso = perfilAcessoList.get(perfilAcessoList.size() - 1);
        assertThat(testPerfilAcesso.getDescricao()).isEqualTo(DEFAULT_DESCRICAO);
    }

    @Test
    @Transactional
    public void createPerfilAcessoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = perfilAcessoRepository.findAll().size();

        // Create the PerfilAcesso with an existing ID
        perfilAcesso.setId(1L);
        PerfilAcessoDTO perfilAcessoDTO = perfilAcessoMapper.toDto(perfilAcesso);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPerfilAcessoMockMvc.perform(post("/api/perfil-acessos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilAcessoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilAcesso in the database
        List<PerfilAcesso> perfilAcessoList = perfilAcessoRepository.findAll();
        assertThat(perfilAcessoList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPerfilAcessos() throws Exception {
        // Initialize the database
        perfilAcessoRepository.saveAndFlush(perfilAcesso);

        // Get all the perfilAcessoList
        restPerfilAcessoMockMvc.perform(get("/api/perfil-acessos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(perfilAcesso.getId().intValue())))
            .andExpect(jsonPath("$.[*].descricao").value(hasItem(DEFAULT_DESCRICAO)));
    }
    
    @Test
    @Transactional
    public void getPerfilAcesso() throws Exception {
        // Initialize the database
        perfilAcessoRepository.saveAndFlush(perfilAcesso);

        // Get the perfilAcesso
        restPerfilAcessoMockMvc.perform(get("/api/perfil-acessos/{id}", perfilAcesso.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(perfilAcesso.getId().intValue()))
            .andExpect(jsonPath("$.descricao").value(DEFAULT_DESCRICAO));
    }

    @Test
    @Transactional
    public void getNonExistingPerfilAcesso() throws Exception {
        // Get the perfilAcesso
        restPerfilAcessoMockMvc.perform(get("/api/perfil-acessos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePerfilAcesso() throws Exception {
        // Initialize the database
        perfilAcessoRepository.saveAndFlush(perfilAcesso);

        int databaseSizeBeforeUpdate = perfilAcessoRepository.findAll().size();

        // Update the perfilAcesso
        PerfilAcesso updatedPerfilAcesso = perfilAcessoRepository.findById(perfilAcesso.getId()).get();
        // Disconnect from session so that the updates on updatedPerfilAcesso are not directly saved in db
        em.detach(updatedPerfilAcesso);
        updatedPerfilAcesso
            .descricao(UPDATED_DESCRICAO);
        PerfilAcessoDTO perfilAcessoDTO = perfilAcessoMapper.toDto(updatedPerfilAcesso);

        restPerfilAcessoMockMvc.perform(put("/api/perfil-acessos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilAcessoDTO)))
            .andExpect(status().isOk());

        // Validate the PerfilAcesso in the database
        List<PerfilAcesso> perfilAcessoList = perfilAcessoRepository.findAll();
        assertThat(perfilAcessoList).hasSize(databaseSizeBeforeUpdate);
        PerfilAcesso testPerfilAcesso = perfilAcessoList.get(perfilAcessoList.size() - 1);
        assertThat(testPerfilAcesso.getDescricao()).isEqualTo(UPDATED_DESCRICAO);
    }

    @Test
    @Transactional
    public void updateNonExistingPerfilAcesso() throws Exception {
        int databaseSizeBeforeUpdate = perfilAcessoRepository.findAll().size();

        // Create the PerfilAcesso
        PerfilAcessoDTO perfilAcessoDTO = perfilAcessoMapper.toDto(perfilAcesso);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPerfilAcessoMockMvc.perform(put("/api/perfil-acessos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(perfilAcessoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PerfilAcesso in the database
        List<PerfilAcesso> perfilAcessoList = perfilAcessoRepository.findAll();
        assertThat(perfilAcessoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePerfilAcesso() throws Exception {
        // Initialize the database
        perfilAcessoRepository.saveAndFlush(perfilAcesso);

        int databaseSizeBeforeDelete = perfilAcessoRepository.findAll().size();

        // Delete the perfilAcesso
        restPerfilAcessoMockMvc.perform(delete("/api/perfil-acessos/{id}", perfilAcesso.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PerfilAcesso> perfilAcessoList = perfilAcessoRepository.findAll();
        assertThat(perfilAcessoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

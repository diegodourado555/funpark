package br.com.web.rest;

import br.com.FunparkApp;
import br.com.domain.MenuPerfil;
import br.com.repository.MenuPerfilRepository;
import br.com.service.MenuPerfilService;
import br.com.service.dto.MenuPerfilDTO;
import br.com.service.mapper.MenuPerfilMapper;
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
 * Integration tests for the {@link MenuPerfilResource} REST controller.
 */
@SpringBootTest(classes = FunparkApp.class)
public class MenuPerfilResourceIT {

    private static final Long DEFAULT_ID_MENU = 1L;
    private static final Long UPDATED_ID_MENU = 2L;

    private static final Long DEFAULT_ID_PERFIL = 1L;
    private static final Long UPDATED_ID_PERFIL = 2L;

    @Autowired
    private MenuPerfilRepository menuPerfilRepository;

    @Autowired
    private MenuPerfilMapper menuPerfilMapper;

    @Autowired
    private MenuPerfilService menuPerfilService;

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

    private MockMvc restMenuPerfilMockMvc;

    private MenuPerfil menuPerfil;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MenuPerfilResource menuPerfilResource = new MenuPerfilResource(menuPerfilService);
        this.restMenuPerfilMockMvc = MockMvcBuilders.standaloneSetup(menuPerfilResource)
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
    public static MenuPerfil createEntity(EntityManager em) {
        MenuPerfil menuPerfil = new MenuPerfil()
            .idMenu(DEFAULT_ID_MENU)
            .idPerfil(DEFAULT_ID_PERFIL);
        return menuPerfil;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MenuPerfil createUpdatedEntity(EntityManager em) {
        MenuPerfil menuPerfil = new MenuPerfil()
            .idMenu(UPDATED_ID_MENU)
            .idPerfil(UPDATED_ID_PERFIL);
        return menuPerfil;
    }

    @BeforeEach
    public void initTest() {
        menuPerfil = createEntity(em);
    }

    @Test
    @Transactional
    public void createMenuPerfil() throws Exception {
        int databaseSizeBeforeCreate = menuPerfilRepository.findAll().size();

        // Create the MenuPerfil
        MenuPerfilDTO menuPerfilDTO = menuPerfilMapper.toDto(menuPerfil);
        restMenuPerfilMockMvc.perform(post("/api/menu-perfils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuPerfilDTO)))
            .andExpect(status().isCreated());

        // Validate the MenuPerfil in the database
        List<MenuPerfil> menuPerfilList = menuPerfilRepository.findAll();
        assertThat(menuPerfilList).hasSize(databaseSizeBeforeCreate + 1);
        MenuPerfil testMenuPerfil = menuPerfilList.get(menuPerfilList.size() - 1);
        assertThat(testMenuPerfil.getIdMenu()).isEqualTo(DEFAULT_ID_MENU);
        assertThat(testMenuPerfil.getIdPerfil()).isEqualTo(DEFAULT_ID_PERFIL);
    }

    @Test
    @Transactional
    public void createMenuPerfilWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = menuPerfilRepository.findAll().size();

        // Create the MenuPerfil with an existing ID
        menuPerfil.setId(1L);
        MenuPerfilDTO menuPerfilDTO = menuPerfilMapper.toDto(menuPerfil);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMenuPerfilMockMvc.perform(post("/api/menu-perfils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuPerfilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuPerfil in the database
        List<MenuPerfil> menuPerfilList = menuPerfilRepository.findAll();
        assertThat(menuPerfilList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMenuPerfils() throws Exception {
        // Initialize the database
        menuPerfilRepository.saveAndFlush(menuPerfil);

        // Get all the menuPerfilList
        restMenuPerfilMockMvc.perform(get("/api/menu-perfils?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(menuPerfil.getId().intValue())))
            .andExpect(jsonPath("$.[*].idMenu").value(hasItem(DEFAULT_ID_MENU.intValue())))
            .andExpect(jsonPath("$.[*].idPerfil").value(hasItem(DEFAULT_ID_PERFIL.intValue())));
    }
    
    @Test
    @Transactional
    public void getMenuPerfil() throws Exception {
        // Initialize the database
        menuPerfilRepository.saveAndFlush(menuPerfil);

        // Get the menuPerfil
        restMenuPerfilMockMvc.perform(get("/api/menu-perfils/{id}", menuPerfil.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(menuPerfil.getId().intValue()))
            .andExpect(jsonPath("$.idMenu").value(DEFAULT_ID_MENU.intValue()))
            .andExpect(jsonPath("$.idPerfil").value(DEFAULT_ID_PERFIL.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMenuPerfil() throws Exception {
        // Get the menuPerfil
        restMenuPerfilMockMvc.perform(get("/api/menu-perfils/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMenuPerfil() throws Exception {
        // Initialize the database
        menuPerfilRepository.saveAndFlush(menuPerfil);

        int databaseSizeBeforeUpdate = menuPerfilRepository.findAll().size();

        // Update the menuPerfil
        MenuPerfil updatedMenuPerfil = menuPerfilRepository.findById(menuPerfil.getId()).get();
        // Disconnect from session so that the updates on updatedMenuPerfil are not directly saved in db
        em.detach(updatedMenuPerfil);
        updatedMenuPerfil
            .idMenu(UPDATED_ID_MENU)
            .idPerfil(UPDATED_ID_PERFIL);
        MenuPerfilDTO menuPerfilDTO = menuPerfilMapper.toDto(updatedMenuPerfil);

        restMenuPerfilMockMvc.perform(put("/api/menu-perfils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuPerfilDTO)))
            .andExpect(status().isOk());

        // Validate the MenuPerfil in the database
        List<MenuPerfil> menuPerfilList = menuPerfilRepository.findAll();
        assertThat(menuPerfilList).hasSize(databaseSizeBeforeUpdate);
        MenuPerfil testMenuPerfil = menuPerfilList.get(menuPerfilList.size() - 1);
        assertThat(testMenuPerfil.getIdMenu()).isEqualTo(UPDATED_ID_MENU);
        assertThat(testMenuPerfil.getIdPerfil()).isEqualTo(UPDATED_ID_PERFIL);
    }

    @Test
    @Transactional
    public void updateNonExistingMenuPerfil() throws Exception {
        int databaseSizeBeforeUpdate = menuPerfilRepository.findAll().size();

        // Create the MenuPerfil
        MenuPerfilDTO menuPerfilDTO = menuPerfilMapper.toDto(menuPerfil);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMenuPerfilMockMvc.perform(put("/api/menu-perfils")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(menuPerfilDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MenuPerfil in the database
        List<MenuPerfil> menuPerfilList = menuPerfilRepository.findAll();
        assertThat(menuPerfilList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMenuPerfil() throws Exception {
        // Initialize the database
        menuPerfilRepository.saveAndFlush(menuPerfil);

        int databaseSizeBeforeDelete = menuPerfilRepository.findAll().size();

        // Delete the menuPerfil
        restMenuPerfilMockMvc.perform(delete("/api/menu-perfils/{id}", menuPerfil.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MenuPerfil> menuPerfilList = menuPerfilRepository.findAll();
        assertThat(menuPerfilList).hasSize(databaseSizeBeforeDelete - 1);
    }
}

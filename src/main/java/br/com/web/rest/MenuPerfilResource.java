package br.com.web.rest;

import br.com.service.MenuPerfilService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.MenuPerfilDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link br.com.domain.MenuPerfil}.
 */
@RestController
@RequestMapping("/api")
public class MenuPerfilResource {

    private final Logger log = LoggerFactory.getLogger(MenuPerfilResource.class);

    private static final String ENTITY_NAME = "menuPerfil";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MenuPerfilService menuPerfilService;

    public MenuPerfilResource(MenuPerfilService menuPerfilService) {
        this.menuPerfilService = menuPerfilService;
    }

    /**
     * {@code POST  /menu-perfils} : Create a new menuPerfil.
     *
     * @param menuPerfilDTO the menuPerfilDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new menuPerfilDTO, or with status {@code 400 (Bad Request)} if the menuPerfil has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/menu-perfils")
    public ResponseEntity<MenuPerfilDTO> createMenuPerfil(@RequestBody MenuPerfilDTO menuPerfilDTO) throws URISyntaxException {
        log.debug("REST request to save MenuPerfil : {}", menuPerfilDTO);
        if (menuPerfilDTO.getId() != null) {
            throw new BadRequestAlertException("A new menuPerfil cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MenuPerfilDTO result = menuPerfilService.save(menuPerfilDTO);
        return ResponseEntity.created(new URI("/api/menu-perfils/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /menu-perfils} : Updates an existing menuPerfil.
     *
     * @param menuPerfilDTO the menuPerfilDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated menuPerfilDTO,
     * or with status {@code 400 (Bad Request)} if the menuPerfilDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the menuPerfilDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/menu-perfils")
    public ResponseEntity<MenuPerfilDTO> updateMenuPerfil(@RequestBody MenuPerfilDTO menuPerfilDTO) throws URISyntaxException {
        log.debug("REST request to update MenuPerfil : {}", menuPerfilDTO);
        if (menuPerfilDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MenuPerfilDTO result = menuPerfilService.save(menuPerfilDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, menuPerfilDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /menu-perfils} : get all the menuPerfils.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of menuPerfils in body.
     */
    @GetMapping("/menu-perfils")
    public ResponseEntity<List<MenuPerfilDTO>> getAllMenuPerfils(Pageable pageable) {
        log.debug("REST request to get a page of MenuPerfils");
        Page<MenuPerfilDTO> page = menuPerfilService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /menu-perfils/:id} : get the "id" menuPerfil.
     *
     * @param id the id of the menuPerfilDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the menuPerfilDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/menu-perfils/{id}")
    public ResponseEntity<MenuPerfilDTO> getMenuPerfil(@PathVariable Long id) {
        log.debug("REST request to get MenuPerfil : {}", id);
        Optional<MenuPerfilDTO> menuPerfilDTO = menuPerfilService.findOne(id);
        return ResponseUtil.wrapOrNotFound(menuPerfilDTO);
    }

    /**
     * {@code DELETE  /menu-perfils/:id} : delete the "id" menuPerfil.
     *
     * @param id the id of the menuPerfilDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/menu-perfils/{id}")
    public ResponseEntity<Void> deleteMenuPerfil(@PathVariable Long id) {
        log.debug("REST request to delete MenuPerfil : {}", id);
        menuPerfilService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package br.com.web.rest;

import br.com.service.PerfilAcessoService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.PerfilAcessoDTO;

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
 * REST controller for managing {@link br.com.domain.PerfilAcesso}.
 */
@RestController
@RequestMapping("/api")
public class PerfilAcessoResource {

    private final Logger log = LoggerFactory.getLogger(PerfilAcessoResource.class);

    private static final String ENTITY_NAME = "perfilAcesso";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PerfilAcessoService perfilAcessoService;

    public PerfilAcessoResource(PerfilAcessoService perfilAcessoService) {
        this.perfilAcessoService = perfilAcessoService;
    }

    /**
     * {@code POST  /perfil-acessos} : Create a new perfilAcesso.
     *
     * @param perfilAcessoDTO the perfilAcessoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new perfilAcessoDTO, or with status {@code 400 (Bad Request)} if the perfilAcesso has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/perfil-acessos")
    public ResponseEntity<PerfilAcessoDTO> createPerfilAcesso(@RequestBody PerfilAcessoDTO perfilAcessoDTO) throws URISyntaxException {
        log.debug("REST request to save PerfilAcesso : {}", perfilAcessoDTO);
        if (perfilAcessoDTO.getId() != null) {
            throw new BadRequestAlertException("A new perfilAcesso cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PerfilAcessoDTO result = perfilAcessoService.save(perfilAcessoDTO);
        return ResponseEntity.created(new URI("/api/perfil-acessos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /perfil-acessos} : Updates an existing perfilAcesso.
     *
     * @param perfilAcessoDTO the perfilAcessoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated perfilAcessoDTO,
     * or with status {@code 400 (Bad Request)} if the perfilAcessoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the perfilAcessoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/perfil-acessos")
    public ResponseEntity<PerfilAcessoDTO> updatePerfilAcesso(@RequestBody PerfilAcessoDTO perfilAcessoDTO) throws URISyntaxException {
        log.debug("REST request to update PerfilAcesso : {}", perfilAcessoDTO);
        if (perfilAcessoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PerfilAcessoDTO result = perfilAcessoService.save(perfilAcessoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, perfilAcessoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /perfil-acessos} : get all the perfilAcessos.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of perfilAcessos in body.
     */
    @GetMapping("/perfil-acessos")
    public ResponseEntity<List<PerfilAcessoDTO>> getAllPerfilAcessos(Pageable pageable) {
        log.debug("REST request to get a page of PerfilAcessos");
        Page<PerfilAcessoDTO> page = perfilAcessoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /perfil-acessos/:id} : get the "id" perfilAcesso.
     *
     * @param id the id of the perfilAcessoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the perfilAcessoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/perfil-acessos/{id}")
    public ResponseEntity<PerfilAcessoDTO> getPerfilAcesso(@PathVariable Long id) {
        log.debug("REST request to get PerfilAcesso : {}", id);
        Optional<PerfilAcessoDTO> perfilAcessoDTO = perfilAcessoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(perfilAcessoDTO);
    }

    /**
     * {@code DELETE  /perfil-acessos/:id} : delete the "id" perfilAcesso.
     *
     * @param id the id of the perfilAcessoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/perfil-acessos/{id}")
    public ResponseEntity<Void> deletePerfilAcesso(@PathVariable Long id) {
        log.debug("REST request to delete PerfilAcesso : {}", id);
        perfilAcessoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package br.com.web.rest;

import br.com.service.GrupoMaquinaService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.GrupoMaquinaDTO;

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
 * REST controller for managing {@link br.com.domain.GrupoMaquina}.
 */
@RestController
@RequestMapping("/api")
public class GrupoMaquinaResource {

    private final Logger log = LoggerFactory.getLogger(GrupoMaquinaResource.class);

    private static final String ENTITY_NAME = "grupoMaquina";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GrupoMaquinaService grupoMaquinaService;

    public GrupoMaquinaResource(GrupoMaquinaService grupoMaquinaService) {
        this.grupoMaquinaService = grupoMaquinaService;
    }

    /**
     * {@code POST  /grupo-maquinas} : Create a new grupoMaquina.
     *
     * @param grupoMaquinaDTO the grupoMaquinaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new grupoMaquinaDTO, or with status {@code 400 (Bad Request)} if the grupoMaquina has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/grupo-maquinas")
    public ResponseEntity<GrupoMaquinaDTO> createGrupoMaquina(@RequestBody GrupoMaquinaDTO grupoMaquinaDTO) throws URISyntaxException {
        log.debug("REST request to save GrupoMaquina : {}", grupoMaquinaDTO);
        if (grupoMaquinaDTO.getId() != null) {
            throw new BadRequestAlertException("A new grupoMaquina cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GrupoMaquinaDTO result = grupoMaquinaService.save(grupoMaquinaDTO);
        return ResponseEntity.created(new URI("/api/grupo-maquinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /grupo-maquinas} : Updates an existing grupoMaquina.
     *
     * @param grupoMaquinaDTO the grupoMaquinaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated grupoMaquinaDTO,
     * or with status {@code 400 (Bad Request)} if the grupoMaquinaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the grupoMaquinaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/grupo-maquinas")
    public ResponseEntity<GrupoMaquinaDTO> updateGrupoMaquina(@RequestBody GrupoMaquinaDTO grupoMaquinaDTO) throws URISyntaxException {
        log.debug("REST request to update GrupoMaquina : {}", grupoMaquinaDTO);
        if (grupoMaquinaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GrupoMaquinaDTO result = grupoMaquinaService.save(grupoMaquinaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, grupoMaquinaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /grupo-maquinas} : get all the grupoMaquinas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of grupoMaquinas in body.
     */
    @GetMapping("/grupo-maquinas")
    public ResponseEntity<List<GrupoMaquinaDTO>> getAllGrupoMaquinas(Pageable pageable) {
        log.debug("REST request to get a page of GrupoMaquinas");
        Page<GrupoMaquinaDTO> page = grupoMaquinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /grupo-maquinas/:id} : get the "id" grupoMaquina.
     *
     * @param id the id of the grupoMaquinaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the grupoMaquinaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/grupo-maquinas/{id}")
    public ResponseEntity<GrupoMaquinaDTO> getGrupoMaquina(@PathVariable Long id) {
        log.debug("REST request to get GrupoMaquina : {}", id);
        Optional<GrupoMaquinaDTO> grupoMaquinaDTO = grupoMaquinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(grupoMaquinaDTO);
    }

    /**
     * {@code DELETE  /grupo-maquinas/:id} : delete the "id" grupoMaquina.
     *
     * @param id the id of the grupoMaquinaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/grupo-maquinas/{id}")
    public ResponseEntity<Void> deleteGrupoMaquina(@PathVariable Long id) {
        log.debug("REST request to delete GrupoMaquina : {}", id);
        grupoMaquinaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

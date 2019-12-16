package br.com.web.rest;

import br.com.service.LojaMaquinaService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.LojaMaquinaDTO;

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
 * REST controller for managing {@link br.com.domain.LojaMaquina}.
 */
@RestController
@RequestMapping("/api")
public class LojaMaquinaResource {

    private final Logger log = LoggerFactory.getLogger(LojaMaquinaResource.class);

    private static final String ENTITY_NAME = "lojaMaquina";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LojaMaquinaService lojaMaquinaService;

    public LojaMaquinaResource(LojaMaquinaService lojaMaquinaService) {
        this.lojaMaquinaService = lojaMaquinaService;
    }

    /**
     * {@code POST  /loja-maquinas} : Create a new lojaMaquina.
     *
     * @param lojaMaquinaDTO the lojaMaquinaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lojaMaquinaDTO, or with status {@code 400 (Bad Request)} if the lojaMaquina has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/loja-maquinas")
    public ResponseEntity<LojaMaquinaDTO> createLojaMaquina(@RequestBody LojaMaquinaDTO lojaMaquinaDTO) throws URISyntaxException {
        log.debug("REST request to save LojaMaquina : {}", lojaMaquinaDTO);
        if (lojaMaquinaDTO.getId() != null) {
            throw new BadRequestAlertException("A new lojaMaquina cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LojaMaquinaDTO result = lojaMaquinaService.save(lojaMaquinaDTO);
        return ResponseEntity.created(new URI("/api/loja-maquinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /loja-maquinas} : Updates an existing lojaMaquina.
     *
     * @param lojaMaquinaDTO the lojaMaquinaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lojaMaquinaDTO,
     * or with status {@code 400 (Bad Request)} if the lojaMaquinaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lojaMaquinaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/loja-maquinas")
    public ResponseEntity<LojaMaquinaDTO> updateLojaMaquina(@RequestBody LojaMaquinaDTO lojaMaquinaDTO) throws URISyntaxException {
        log.debug("REST request to update LojaMaquina : {}", lojaMaquinaDTO);
        if (lojaMaquinaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LojaMaquinaDTO result = lojaMaquinaService.save(lojaMaquinaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lojaMaquinaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /loja-maquinas} : get all the lojaMaquinas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lojaMaquinas in body.
     */
    @GetMapping("/loja-maquinas")
    public ResponseEntity<List<LojaMaquinaDTO>> getAllLojaMaquinas(Pageable pageable) {
        log.debug("REST request to get a page of LojaMaquinas");
        Page<LojaMaquinaDTO> page = lojaMaquinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /loja-maquinas/:id} : get the "id" lojaMaquina.
     *
     * @param id the id of the lojaMaquinaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lojaMaquinaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/loja-maquinas/{id}")
    public ResponseEntity<LojaMaquinaDTO> getLojaMaquina(@PathVariable Long id) {
        log.debug("REST request to get LojaMaquina : {}", id);
        Optional<LojaMaquinaDTO> lojaMaquinaDTO = lojaMaquinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lojaMaquinaDTO);
    }

    /**
     * {@code DELETE  /loja-maquinas/:id} : delete the "id" lojaMaquina.
     *
     * @param id the id of the lojaMaquinaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/loja-maquinas/{id}")
    public ResponseEntity<Void> deleteLojaMaquina(@PathVariable Long id) {
        log.debug("REST request to delete LojaMaquina : {}", id);
        lojaMaquinaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

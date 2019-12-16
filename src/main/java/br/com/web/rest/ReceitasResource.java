package br.com.web.rest;

import br.com.service.ReceitasService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.ReceitasDTO;

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
 * REST controller for managing {@link br.com.domain.Receitas}.
 */
@RestController
@RequestMapping("/api")
public class ReceitasResource {

    private final Logger log = LoggerFactory.getLogger(ReceitasResource.class);

    private static final String ENTITY_NAME = "receitas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ReceitasService receitasService;

    public ReceitasResource(ReceitasService receitasService) {
        this.receitasService = receitasService;
    }

    /**
     * {@code POST  /receitas} : Create a new receitas.
     *
     * @param receitasDTO the receitasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new receitasDTO, or with status {@code 400 (Bad Request)} if the receitas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receitas")
    public ResponseEntity<ReceitasDTO> createReceitas(@RequestBody ReceitasDTO receitasDTO) throws URISyntaxException {
        log.debug("REST request to save Receitas : {}", receitasDTO);
        if (receitasDTO.getId() != null) {
            throw new BadRequestAlertException("A new receitas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReceitasDTO result = receitasService.save(receitasDTO);
        return ResponseEntity.created(new URI("/api/receitas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /receitas} : Updates an existing receitas.
     *
     * @param receitasDTO the receitasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated receitasDTO,
     * or with status {@code 400 (Bad Request)} if the receitasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the receitasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/receitas")
    public ResponseEntity<ReceitasDTO> updateReceitas(@RequestBody ReceitasDTO receitasDTO) throws URISyntaxException {
        log.debug("REST request to update Receitas : {}", receitasDTO);
        if (receitasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReceitasDTO result = receitasService.save(receitasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, receitasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /receitas} : get all the receitas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of receitas in body.
     */
    @GetMapping("/receitas")
    public ResponseEntity<List<ReceitasDTO>> getAllReceitas(Pageable pageable) {
        log.debug("REST request to get a page of Receitas");
        Page<ReceitasDTO> page = receitasService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /receitas/:id} : get the "id" receitas.
     *
     * @param id the id of the receitasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the receitasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receitas/{id}")
    public ResponseEntity<ReceitasDTO> getReceitas(@PathVariable Long id) {
        log.debug("REST request to get Receitas : {}", id);
        Optional<ReceitasDTO> receitasDTO = receitasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(receitasDTO);
    }

    /**
     * {@code DELETE  /receitas/:id} : delete the "id" receitas.
     *
     * @param id the id of the receitasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/receitas/{id}")
    public ResponseEntity<Void> deleteReceitas(@PathVariable Long id) {
        log.debug("REST request to delete Receitas : {}", id);
        receitasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

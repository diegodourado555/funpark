package br.com.web.rest;

import br.com.service.DespesasService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.DespesasDTO;

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
 * REST controller for managing {@link br.com.domain.Despesas}.
 */
@RestController
@RequestMapping("/api")
public class DespesasResource {

    private final Logger log = LoggerFactory.getLogger(DespesasResource.class);

    private static final String ENTITY_NAME = "despesas";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DespesasService despesasService;

    public DespesasResource(DespesasService despesasService) {
        this.despesasService = despesasService;
    }

    /**
     * {@code POST  /despesas} : Create a new despesas.
     *
     * @param despesasDTO the despesasDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new despesasDTO, or with status {@code 400 (Bad Request)} if the despesas has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/despesas")
    public ResponseEntity<DespesasDTO> createDespesas(@RequestBody DespesasDTO despesasDTO) throws URISyntaxException {
        log.debug("REST request to save Despesas : {}", despesasDTO);
        if (despesasDTO.getId() != null) {
            throw new BadRequestAlertException("A new despesas cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DespesasDTO result = despesasService.save(despesasDTO);
        return ResponseEntity.created(new URI("/api/despesas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /despesas} : Updates an existing despesas.
     *
     * @param despesasDTO the despesasDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated despesasDTO,
     * or with status {@code 400 (Bad Request)} if the despesasDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the despesasDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/despesas")
    public ResponseEntity<DespesasDTO> updateDespesas(@RequestBody DespesasDTO despesasDTO) throws URISyntaxException {
        log.debug("REST request to update Despesas : {}", despesasDTO);
        if (despesasDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DespesasDTO result = despesasService.save(despesasDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, despesasDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /despesas} : get all the despesas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of despesas in body.
     */
    @GetMapping("/despesas")
    public ResponseEntity<List<DespesasDTO>> getAllDespesas(Pageable pageable) {
        log.debug("REST request to get a page of Despesas");
        Page<DespesasDTO> page = despesasService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /despesas/:id} : get the "id" despesas.
     *
     * @param id the id of the despesasDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the despesasDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/despesas/{id}")
    public ResponseEntity<DespesasDTO> getDespesas(@PathVariable Long id) {
        log.debug("REST request to get Despesas : {}", id);
        Optional<DespesasDTO> despesasDTO = despesasService.findOne(id);
        return ResponseUtil.wrapOrNotFound(despesasDTO);
    }

    /**
     * {@code DELETE  /despesas/:id} : delete the "id" despesas.
     *
     * @param id the id of the despesasDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/despesas/{id}")
    public ResponseEntity<Void> deleteDespesas(@PathVariable Long id) {
        log.debug("REST request to delete Despesas : {}", id);
        despesasService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

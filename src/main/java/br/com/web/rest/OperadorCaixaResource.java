package br.com.web.rest;

import br.com.service.OperadorCaixaService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.OperadorCaixaDTO;

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
 * REST controller for managing {@link br.com.domain.OperadorCaixa}.
 */
@RestController
@RequestMapping("/api")
public class OperadorCaixaResource {

    private final Logger log = LoggerFactory.getLogger(OperadorCaixaResource.class);

    private static final String ENTITY_NAME = "operadorCaixa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OperadorCaixaService operadorCaixaService;

    public OperadorCaixaResource(OperadorCaixaService operadorCaixaService) {
        this.operadorCaixaService = operadorCaixaService;
    }

    /**
     * {@code POST  /operador-caixas} : Create a new operadorCaixa.
     *
     * @param operadorCaixaDTO the operadorCaixaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new operadorCaixaDTO, or with status {@code 400 (Bad Request)} if the operadorCaixa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/operador-caixas")
    public ResponseEntity<OperadorCaixaDTO> createOperadorCaixa(@RequestBody OperadorCaixaDTO operadorCaixaDTO) throws URISyntaxException {
        log.debug("REST request to save OperadorCaixa : {}", operadorCaixaDTO);
        if (operadorCaixaDTO.getId() != null) {
            throw new BadRequestAlertException("A new operadorCaixa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OperadorCaixaDTO result = operadorCaixaService.save(operadorCaixaDTO);
        return ResponseEntity.created(new URI("/api/operador-caixas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /operador-caixas} : Updates an existing operadorCaixa.
     *
     * @param operadorCaixaDTO the operadorCaixaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated operadorCaixaDTO,
     * or with status {@code 400 (Bad Request)} if the operadorCaixaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the operadorCaixaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/operador-caixas")
    public ResponseEntity<OperadorCaixaDTO> updateOperadorCaixa(@RequestBody OperadorCaixaDTO operadorCaixaDTO) throws URISyntaxException {
        log.debug("REST request to update OperadorCaixa : {}", operadorCaixaDTO);
        if (operadorCaixaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        OperadorCaixaDTO result = operadorCaixaService.save(operadorCaixaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, operadorCaixaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /operador-caixas} : get all the operadorCaixas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of operadorCaixas in body.
     */
    @GetMapping("/operador-caixas")
    public ResponseEntity<List<OperadorCaixaDTO>> getAllOperadorCaixas(Pageable pageable) {
        log.debug("REST request to get a page of OperadorCaixas");
        Page<OperadorCaixaDTO> page = operadorCaixaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /operador-caixas/:id} : get the "id" operadorCaixa.
     *
     * @param id the id of the operadorCaixaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the operadorCaixaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/operador-caixas/{id}")
    public ResponseEntity<OperadorCaixaDTO> getOperadorCaixa(@PathVariable Long id) {
        log.debug("REST request to get OperadorCaixa : {}", id);
        Optional<OperadorCaixaDTO> operadorCaixaDTO = operadorCaixaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(operadorCaixaDTO);
    }

    /**
     * {@code DELETE  /operador-caixas/:id} : delete the "id" operadorCaixa.
     *
     * @param id the id of the operadorCaixaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/operador-caixas/{id}")
    public ResponseEntity<Void> deleteOperadorCaixa(@PathVariable Long id) {
        log.debug("REST request to delete OperadorCaixa : {}", id);
        operadorCaixaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

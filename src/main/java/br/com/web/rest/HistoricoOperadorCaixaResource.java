package br.com.web.rest;

import br.com.service.HistoricoOperadorCaixaService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.HistoricoOperadorCaixaDTO;

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
 * REST controller for managing {@link br.com.domain.HistoricoOperadorCaixa}.
 */
@RestController
@RequestMapping("/api")
public class HistoricoOperadorCaixaResource {

    private final Logger log = LoggerFactory.getLogger(HistoricoOperadorCaixaResource.class);

    private static final String ENTITY_NAME = "historicoOperadorCaixa";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoricoOperadorCaixaService historicoOperadorCaixaService;

    public HistoricoOperadorCaixaResource(HistoricoOperadorCaixaService historicoOperadorCaixaService) {
        this.historicoOperadorCaixaService = historicoOperadorCaixaService;
    }

    /**
     * {@code POST  /historico-operador-caixas} : Create a new historicoOperadorCaixa.
     *
     * @param historicoOperadorCaixaDTO the historicoOperadorCaixaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historicoOperadorCaixaDTO, or with status {@code 400 (Bad Request)} if the historicoOperadorCaixa has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historico-operador-caixas")
    public ResponseEntity<HistoricoOperadorCaixaDTO> createHistoricoOperadorCaixa(@RequestBody HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO) throws URISyntaxException {
        log.debug("REST request to save HistoricoOperadorCaixa : {}", historicoOperadorCaixaDTO);
        if (historicoOperadorCaixaDTO.getId() != null) {
            throw new BadRequestAlertException("A new historicoOperadorCaixa cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoricoOperadorCaixaDTO result = historicoOperadorCaixaService.save(historicoOperadorCaixaDTO);
        return ResponseEntity.created(new URI("/api/historico-operador-caixas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /historico-operador-caixas} : Updates an existing historicoOperadorCaixa.
     *
     * @param historicoOperadorCaixaDTO the historicoOperadorCaixaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historicoOperadorCaixaDTO,
     * or with status {@code 400 (Bad Request)} if the historicoOperadorCaixaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historicoOperadorCaixaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historico-operador-caixas")
    public ResponseEntity<HistoricoOperadorCaixaDTO> updateHistoricoOperadorCaixa(@RequestBody HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO) throws URISyntaxException {
        log.debug("REST request to update HistoricoOperadorCaixa : {}", historicoOperadorCaixaDTO);
        if (historicoOperadorCaixaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoricoOperadorCaixaDTO result = historicoOperadorCaixaService.save(historicoOperadorCaixaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historicoOperadorCaixaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /historico-operador-caixas} : get all the historicoOperadorCaixas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historicoOperadorCaixas in body.
     */
    @GetMapping("/historico-operador-caixas")
    public ResponseEntity<List<HistoricoOperadorCaixaDTO>> getAllHistoricoOperadorCaixas(Pageable pageable) {
        log.debug("REST request to get a page of HistoricoOperadorCaixas");
        Page<HistoricoOperadorCaixaDTO> page = historicoOperadorCaixaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historico-operador-caixas/:id} : get the "id" historicoOperadorCaixa.
     *
     * @param id the id of the historicoOperadorCaixaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historicoOperadorCaixaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historico-operador-caixas/{id}")
    public ResponseEntity<HistoricoOperadorCaixaDTO> getHistoricoOperadorCaixa(@PathVariable Long id) {
        log.debug("REST request to get HistoricoOperadorCaixa : {}", id);
        Optional<HistoricoOperadorCaixaDTO> historicoOperadorCaixaDTO = historicoOperadorCaixaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historicoOperadorCaixaDTO);
    }

    /**
     * {@code DELETE  /historico-operador-caixas/:id} : delete the "id" historicoOperadorCaixa.
     *
     * @param id the id of the historicoOperadorCaixaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historico-operador-caixas/{id}")
    public ResponseEntity<Void> deleteHistoricoOperadorCaixa(@PathVariable Long id) {
        log.debug("REST request to delete HistoricoOperadorCaixa : {}", id);
        historicoOperadorCaixaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

package br.com.web.rest;

import br.com.service.HistoricoMaquinaService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.HistoricoMaquinaDTO;

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
 * REST controller for managing {@link br.com.domain.HistoricoMaquina}.
 */
@RestController
@RequestMapping("/api")
public class HistoricoMaquinaResource {

    private final Logger log = LoggerFactory.getLogger(HistoricoMaquinaResource.class);

    private static final String ENTITY_NAME = "historicoMaquina";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final HistoricoMaquinaService historicoMaquinaService;

    public HistoricoMaquinaResource(HistoricoMaquinaService historicoMaquinaService) {
        this.historicoMaquinaService = historicoMaquinaService;
    }

    /**
     * {@code POST  /historico-maquinas} : Create a new historicoMaquina.
     *
     * @param historicoMaquinaDTO the historicoMaquinaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new historicoMaquinaDTO, or with status {@code 400 (Bad Request)} if the historicoMaquina has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/historico-maquinas")
    public ResponseEntity<HistoricoMaquinaDTO> createHistoricoMaquina(@RequestBody HistoricoMaquinaDTO historicoMaquinaDTO) throws URISyntaxException {
        log.debug("REST request to save HistoricoMaquina : {}", historicoMaquinaDTO);
        if (historicoMaquinaDTO.getId() != null) {
            throw new BadRequestAlertException("A new historicoMaquina cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistoricoMaquinaDTO result = historicoMaquinaService.save(historicoMaquinaDTO);
        return ResponseEntity.created(new URI("/api/historico-maquinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /historico-maquinas} : Updates an existing historicoMaquina.
     *
     * @param historicoMaquinaDTO the historicoMaquinaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated historicoMaquinaDTO,
     * or with status {@code 400 (Bad Request)} if the historicoMaquinaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the historicoMaquinaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/historico-maquinas")
    public ResponseEntity<HistoricoMaquinaDTO> updateHistoricoMaquina(@RequestBody HistoricoMaquinaDTO historicoMaquinaDTO) throws URISyntaxException {
        log.debug("REST request to update HistoricoMaquina : {}", historicoMaquinaDTO);
        if (historicoMaquinaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistoricoMaquinaDTO result = historicoMaquinaService.save(historicoMaquinaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, historicoMaquinaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /historico-maquinas} : get all the historicoMaquinas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of historicoMaquinas in body.
     */
    @GetMapping("/historico-maquinas")
    public ResponseEntity<List<HistoricoMaquinaDTO>> getAllHistoricoMaquinas(Pageable pageable) {
        log.debug("REST request to get a page of HistoricoMaquinas");
        Page<HistoricoMaquinaDTO> page = historicoMaquinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /historico-maquinas/:id} : get the "id" historicoMaquina.
     *
     * @param id the id of the historicoMaquinaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the historicoMaquinaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/historico-maquinas/{id}")
    public ResponseEntity<HistoricoMaquinaDTO> getHistoricoMaquina(@PathVariable Long id) {
        log.debug("REST request to get HistoricoMaquina : {}", id);
        Optional<HistoricoMaquinaDTO> historicoMaquinaDTO = historicoMaquinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(historicoMaquinaDTO);
    }

    /**
     * {@code DELETE  /historico-maquinas/:id} : delete the "id" historicoMaquina.
     *
     * @param id the id of the historicoMaquinaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/historico-maquinas/{id}")
    public ResponseEntity<Void> deleteHistoricoMaquina(@PathVariable Long id) {
        log.debug("REST request to delete HistoricoMaquina : {}", id);
        historicoMaquinaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

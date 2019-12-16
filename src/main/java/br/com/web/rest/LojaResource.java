package br.com.web.rest;

import br.com.service.LojaService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.LojaDTO;

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
 * REST controller for managing {@link br.com.domain.Loja}.
 */
@RestController
@RequestMapping("/api")
public class LojaResource {

    private final Logger log = LoggerFactory.getLogger(LojaResource.class);

    private static final String ENTITY_NAME = "loja";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LojaService lojaService;

    public LojaResource(LojaService lojaService) {
        this.lojaService = lojaService;
    }

    /**
     * {@code POST  /lojas} : Create a new loja.
     *
     * @param lojaDTO the lojaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new lojaDTO, or with status {@code 400 (Bad Request)} if the loja has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/lojas")
    public ResponseEntity<LojaDTO> createLoja(@RequestBody LojaDTO lojaDTO) throws URISyntaxException {
        log.debug("REST request to save Loja : {}", lojaDTO);
        if (lojaDTO.getId() != null) {
            throw new BadRequestAlertException("A new loja cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LojaDTO result = lojaService.save(lojaDTO);
        return ResponseEntity.created(new URI("/api/lojas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /lojas} : Updates an existing loja.
     *
     * @param lojaDTO the lojaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated lojaDTO,
     * or with status {@code 400 (Bad Request)} if the lojaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the lojaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/lojas")
    public ResponseEntity<LojaDTO> updateLoja(@RequestBody LojaDTO lojaDTO) throws URISyntaxException {
        log.debug("REST request to update Loja : {}", lojaDTO);
        if (lojaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LojaDTO result = lojaService.save(lojaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, lojaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /lojas} : get all the lojas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of lojas in body.
     */
    @GetMapping("/lojas")
    public ResponseEntity<List<LojaDTO>> getAllLojas(Pageable pageable) {
        log.debug("REST request to get a page of Lojas");
        Page<LojaDTO> page = lojaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /lojas/:id} : get the "id" loja.
     *
     * @param id the id of the lojaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the lojaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/lojas/{id}")
    public ResponseEntity<LojaDTO> getLoja(@PathVariable Long id) {
        log.debug("REST request to get Loja : {}", id);
        Optional<LojaDTO> lojaDTO = lojaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(lojaDTO);
    }

    /**
     * {@code DELETE  /lojas/:id} : delete the "id" loja.
     *
     * @param id the id of the lojaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/lojas/{id}")
    public ResponseEntity<Void> deleteLoja(@PathVariable Long id) {
        log.debug("REST request to delete Loja : {}", id);
        lojaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

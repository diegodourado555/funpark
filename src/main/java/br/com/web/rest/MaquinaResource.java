package br.com.web.rest;

import br.com.service.MaquinaService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.MaquinaDTO;

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
 * REST controller for managing {@link br.com.domain.Maquina}.
 */
@RestController
@RequestMapping("/api")
public class MaquinaResource {

    private final Logger log = LoggerFactory.getLogger(MaquinaResource.class);

    private static final String ENTITY_NAME = "maquina";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MaquinaService maquinaService;

    public MaquinaResource(MaquinaService maquinaService) {
        this.maquinaService = maquinaService;
    }

    /**
     * {@code POST  /maquinas} : Create a new maquina.
     *
     * @param maquinaDTO the maquinaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new maquinaDTO, or with status {@code 400 (Bad Request)} if the maquina has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/maquinas")
    public ResponseEntity<MaquinaDTO> createMaquina(@RequestBody MaquinaDTO maquinaDTO) throws URISyntaxException {
        log.debug("REST request to save Maquina : {}", maquinaDTO);
        if (maquinaDTO.getId() != null) {
            throw new BadRequestAlertException("A new maquina cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MaquinaDTO result = maquinaService.save(maquinaDTO);
        return ResponseEntity.created(new URI("/api/maquinas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /maquinas} : Updates an existing maquina.
     *
     * @param maquinaDTO the maquinaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated maquinaDTO,
     * or with status {@code 400 (Bad Request)} if the maquinaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the maquinaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/maquinas")
    public ResponseEntity<MaquinaDTO> updateMaquina(@RequestBody MaquinaDTO maquinaDTO) throws URISyntaxException {
        log.debug("REST request to update Maquina : {}", maquinaDTO);
        if (maquinaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MaquinaDTO result = maquinaService.save(maquinaDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, maquinaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /maquinas} : get all the maquinas.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of maquinas in body.
     */
    @GetMapping("/maquinas")
    public ResponseEntity<List<MaquinaDTO>> getAllMaquinas(Pageable pageable) {
        log.debug("REST request to get a page of Maquinas");
        Page<MaquinaDTO> page = maquinaService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /maquinas/:id} : get the "id" maquina.
     *
     * @param id the id of the maquinaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the maquinaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/maquinas/{id}")
    public ResponseEntity<MaquinaDTO> getMaquina(@PathVariable Long id) {
        log.debug("REST request to get Maquina : {}", id);
        Optional<MaquinaDTO> maquinaDTO = maquinaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(maquinaDTO);
    }

    /**
     * {@code DELETE  /maquinas/:id} : delete the "id" maquina.
     *
     * @param id the id of the maquinaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/maquinas/{id}")
    public ResponseEntity<Void> deleteMaquina(@PathVariable Long id) {
        log.debug("REST request to delete Maquina : {}", id);
        maquinaService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

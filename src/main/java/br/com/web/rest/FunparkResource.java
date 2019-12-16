package br.com.web.rest;

import br.com.service.FunparkService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.FunparkDTO;

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
 * REST controller for managing {@link br.com.domain.Funpark}.
 */
@RestController
@RequestMapping("/api")
public class FunparkResource {

    private final Logger log = LoggerFactory.getLogger(FunparkResource.class);

    private static final String ENTITY_NAME = "funpark";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FunparkService funparkService;

    public FunparkResource(FunparkService funparkService) {
        this.funparkService = funparkService;
    }

    /**
     * {@code POST  /funparks} : Create a new funpark.
     *
     * @param funparkDTO the funparkDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new funparkDTO, or with status {@code 400 (Bad Request)} if the funpark has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/funparks")
    public ResponseEntity<FunparkDTO> createFunpark(@RequestBody FunparkDTO funparkDTO) throws URISyntaxException {
        log.debug("REST request to save Funpark : {}", funparkDTO);
        if (funparkDTO.getId() != null) {
            throw new BadRequestAlertException("A new funpark cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FunparkDTO result = funparkService.save(funparkDTO);
        return ResponseEntity.created(new URI("/api/funparks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /funparks} : Updates an existing funpark.
     *
     * @param funparkDTO the funparkDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated funparkDTO,
     * or with status {@code 400 (Bad Request)} if the funparkDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the funparkDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/funparks")
    public ResponseEntity<FunparkDTO> updateFunpark(@RequestBody FunparkDTO funparkDTO) throws URISyntaxException {
        log.debug("REST request to update Funpark : {}", funparkDTO);
        if (funparkDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FunparkDTO result = funparkService.save(funparkDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, funparkDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /funparks} : get all the funparks.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of funparks in body.
     */
    @GetMapping("/funparks")
    public ResponseEntity<List<FunparkDTO>> getAllFunparks(Pageable pageable) {
        log.debug("REST request to get a page of Funparks");
        Page<FunparkDTO> page = funparkService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /funparks/:id} : get the "id" funpark.
     *
     * @param id the id of the funparkDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the funparkDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/funparks/{id}")
    public ResponseEntity<FunparkDTO> getFunpark(@PathVariable Long id) {
        log.debug("REST request to get Funpark : {}", id);
        Optional<FunparkDTO> funparkDTO = funparkService.findOne(id);
        return ResponseUtil.wrapOrNotFound(funparkDTO);
    }

    /**
     * {@code DELETE  /funparks/:id} : delete the "id" funpark.
     *
     * @param id the id of the funparkDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/funparks/{id}")
    public ResponseEntity<Void> deleteFunpark(@PathVariable Long id) {
        log.debug("REST request to delete Funpark : {}", id);
        funparkService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

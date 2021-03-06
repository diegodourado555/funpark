package br.com.web.rest;

import br.com.service.ContaCorrenteService;
import br.com.web.rest.errors.BadRequestAlertException;
import br.com.service.dto.ContaCorrenteDTO;

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
 * REST controller for managing {@link br.com.domain.ContaCorrente}.
 */
@RestController
@RequestMapping("/api")
public class ContaCorrenteResource {

    private final Logger log = LoggerFactory.getLogger(ContaCorrenteResource.class);

    private static final String ENTITY_NAME = "contaCorrente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ContaCorrenteService contaCorrenteService;

    public ContaCorrenteResource(ContaCorrenteService contaCorrenteService) {
        this.contaCorrenteService = contaCorrenteService;
    }

    /**
     * {@code POST  /conta-correntes} : Create a new contaCorrente.
     *
     * @param contaCorrenteDTO the contaCorrenteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new contaCorrenteDTO, or with status {@code 400 (Bad Request)} if the contaCorrente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/conta-correntes")
    public ResponseEntity<ContaCorrenteDTO> createContaCorrente(@RequestBody ContaCorrenteDTO contaCorrenteDTO) throws URISyntaxException {
        log.debug("REST request to save ContaCorrente : {}", contaCorrenteDTO);
        if (contaCorrenteDTO.getId() != null) {
            throw new BadRequestAlertException("A new contaCorrente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ContaCorrenteDTO result = contaCorrenteService.save(contaCorrenteDTO);
        return ResponseEntity.created(new URI("/api/conta-correntes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /conta-correntes} : Updates an existing contaCorrente.
     *
     * @param contaCorrenteDTO the contaCorrenteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated contaCorrenteDTO,
     * or with status {@code 400 (Bad Request)} if the contaCorrenteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the contaCorrenteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/conta-correntes")
    public ResponseEntity<ContaCorrenteDTO> updateContaCorrente(@RequestBody ContaCorrenteDTO contaCorrenteDTO) throws URISyntaxException {
        log.debug("REST request to update ContaCorrente : {}", contaCorrenteDTO);
        if (contaCorrenteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ContaCorrenteDTO result = contaCorrenteService.save(contaCorrenteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, contaCorrenteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /conta-correntes} : get all the contaCorrentes.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of contaCorrentes in body.
     */
    @GetMapping("/conta-correntes")
    public ResponseEntity<List<ContaCorrenteDTO>> getAllContaCorrentes(Pageable pageable) {
        log.debug("REST request to get a page of ContaCorrentes");
        Page<ContaCorrenteDTO> page = contaCorrenteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/conta-correntes/search/{descricao}")
    public ResponseEntity<List<ContaCorrenteDTO>> search(Pageable pageable, @PathVariable String descricao) {
    	log.debug("REST request to get a page of ContaCorrentes by descricao: {}", descricao);
    	Page<ContaCorrenteDTO> page = contaCorrenteService.search(descricao);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
    	return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /conta-correntes/:id} : get the "id" contaCorrente.
     *
     * @param id the id of the contaCorrenteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the contaCorrenteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/conta-correntes/{id}")
    public ResponseEntity<ContaCorrenteDTO> getContaCorrente(@PathVariable Long id) {
        log.debug("REST request to get ContaCorrente : {}", id);
        Optional<ContaCorrenteDTO> contaCorrenteDTO = contaCorrenteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(contaCorrenteDTO);
    }

    /**
     * {@code DELETE  /conta-correntes/:id} : delete the "id" contaCorrente.
     *
     * @param id the id of the contaCorrenteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/conta-correntes/{id}")
    public ResponseEntity<Void> deleteContaCorrente(@PathVariable Long id) {
        log.debug("REST request to delete ContaCorrente : {}", id);
        contaCorrenteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}

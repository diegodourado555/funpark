package br.com.service;

import br.com.service.dto.ContaCorrenteDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.ContaCorrente}.
 */
public interface ContaCorrenteService {

    /**
     * Save a contaCorrente.
     *
     * @param contaCorrenteDTO the entity to save.
     * @return the persisted entity.
     */
    ContaCorrenteDTO save(ContaCorrenteDTO contaCorrenteDTO);

    /**
     * Get all the contaCorrentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ContaCorrenteDTO> findAll(Pageable pageable);


    /**
     * Get the "id" contaCorrente.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ContaCorrenteDTO> findOne(Long id);

    /**
     * Delete the "id" contaCorrente.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

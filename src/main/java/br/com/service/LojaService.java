package br.com.service;

import br.com.service.dto.LojaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.Loja}.
 */
public interface LojaService {

    /**
     * Save a loja.
     *
     * @param lojaDTO the entity to save.
     * @return the persisted entity.
     */
    LojaDTO save(LojaDTO lojaDTO);

    /**
     * Get all the lojas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LojaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" loja.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LojaDTO> findOne(Long id);

    /**
     * Delete the "id" loja.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

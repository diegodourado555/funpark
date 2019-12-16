package br.com.service;

import br.com.service.dto.LojaMaquinaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.LojaMaquina}.
 */
public interface LojaMaquinaService {

    /**
     * Save a lojaMaquina.
     *
     * @param lojaMaquinaDTO the entity to save.
     * @return the persisted entity.
     */
    LojaMaquinaDTO save(LojaMaquinaDTO lojaMaquinaDTO);

    /**
     * Get all the lojaMaquinas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<LojaMaquinaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" lojaMaquina.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LojaMaquinaDTO> findOne(Long id);

    /**
     * Delete the "id" lojaMaquina.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

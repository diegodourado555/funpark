package br.com.service;

import br.com.service.dto.GrupoMaquinaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.GrupoMaquina}.
 */
public interface GrupoMaquinaService {

    /**
     * Save a grupoMaquina.
     *
     * @param grupoMaquinaDTO the entity to save.
     * @return the persisted entity.
     */
    GrupoMaquinaDTO save(GrupoMaquinaDTO grupoMaquinaDTO);

    /**
     * Get all the grupoMaquinas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<GrupoMaquinaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" grupoMaquina.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<GrupoMaquinaDTO> findOne(Long id);

    /**
     * Delete the "id" grupoMaquina.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

package br.com.service;

import br.com.service.dto.MaquinaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.Maquina}.
 */
public interface MaquinaService {

    /**
     * Save a maquina.
     *
     * @param maquinaDTO the entity to save.
     * @return the persisted entity.
     */
    MaquinaDTO save(MaquinaDTO maquinaDTO);

    /**
     * Get all the maquinas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MaquinaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" maquina.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MaquinaDTO> findOne(Long id);

    /**
     * Delete the "id" maquina.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

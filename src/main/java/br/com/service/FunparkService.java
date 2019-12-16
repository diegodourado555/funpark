package br.com.service;

import br.com.service.dto.FunparkDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.Funpark}.
 */
public interface FunparkService {

    /**
     * Save a funpark.
     *
     * @param funparkDTO the entity to save.
     * @return the persisted entity.
     */
    FunparkDTO save(FunparkDTO funparkDTO);

    /**
     * Get all the funparks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FunparkDTO> findAll(Pageable pageable);


    /**
     * Get the "id" funpark.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FunparkDTO> findOne(Long id);

    /**
     * Delete the "id" funpark.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

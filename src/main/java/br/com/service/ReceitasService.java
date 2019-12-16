package br.com.service;

import br.com.service.dto.ReceitasDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.Receitas}.
 */
public interface ReceitasService {

    /**
     * Save a receitas.
     *
     * @param receitasDTO the entity to save.
     * @return the persisted entity.
     */
    ReceitasDTO save(ReceitasDTO receitasDTO);

    /**
     * Get all the receitas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ReceitasDTO> findAll(Pageable pageable);


    /**
     * Get the "id" receitas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ReceitasDTO> findOne(Long id);

    /**
     * Delete the "id" receitas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

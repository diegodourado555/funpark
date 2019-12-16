package br.com.service;

import br.com.service.dto.DespesasDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.Despesas}.
 */
public interface DespesasService {

    /**
     * Save a despesas.
     *
     * @param despesasDTO the entity to save.
     * @return the persisted entity.
     */
    DespesasDTO save(DespesasDTO despesasDTO);

    /**
     * Get all the despesas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DespesasDTO> findAll(Pageable pageable);


    /**
     * Get the "id" despesas.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DespesasDTO> findOne(Long id);

    /**
     * Delete the "id" despesas.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

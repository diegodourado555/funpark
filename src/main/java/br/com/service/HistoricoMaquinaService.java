package br.com.service;

import br.com.service.dto.HistoricoMaquinaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.HistoricoMaquina}.
 */
public interface HistoricoMaquinaService {

    /**
     * Save a historicoMaquina.
     *
     * @param historicoMaquinaDTO the entity to save.
     * @return the persisted entity.
     */
    HistoricoMaquinaDTO save(HistoricoMaquinaDTO historicoMaquinaDTO);

    /**
     * Get all the historicoMaquinas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HistoricoMaquinaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" historicoMaquina.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HistoricoMaquinaDTO> findOne(Long id);

    /**
     * Delete the "id" historicoMaquina.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

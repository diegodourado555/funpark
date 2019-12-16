package br.com.service;

import br.com.service.dto.OperadorCaixaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.OperadorCaixa}.
 */
public interface OperadorCaixaService {

    /**
     * Save a operadorCaixa.
     *
     * @param operadorCaixaDTO the entity to save.
     * @return the persisted entity.
     */
    OperadorCaixaDTO save(OperadorCaixaDTO operadorCaixaDTO);

    /**
     * Get all the operadorCaixas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OperadorCaixaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" operadorCaixa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OperadorCaixaDTO> findOne(Long id);

    /**
     * Delete the "id" operadorCaixa.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

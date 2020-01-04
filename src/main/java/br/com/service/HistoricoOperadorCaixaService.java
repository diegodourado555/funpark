package br.com.service;

import br.com.service.dto.HistoricoOperadorCaixaDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.HistoricoOperadorCaixa}.
 */
public interface HistoricoOperadorCaixaService {

    /**
     * Save a historicoOperadorCaixa.
     *
     * @param historicoOperadorCaixaDTO the entity to save.
     * @return the persisted entity.
     */
    HistoricoOperadorCaixaDTO save(HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO);

    /**
     * Get all the historicoOperadorCaixas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<HistoricoOperadorCaixaDTO> findAll(Pageable pageable);


    /**
     * Get the "id" historicoOperadorCaixa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<HistoricoOperadorCaixaDTO> findOne(Long id);

    /**
     * Delete the "id" historicoOperadorCaixa.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

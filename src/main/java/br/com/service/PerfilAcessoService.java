package br.com.service;

import br.com.service.dto.PerfilAcessoDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.PerfilAcesso}.
 */
public interface PerfilAcessoService {

    /**
     * Save a perfilAcesso.
     *
     * @param perfilAcessoDTO the entity to save.
     * @return the persisted entity.
     */
    PerfilAcessoDTO save(PerfilAcessoDTO perfilAcessoDTO);

    /**
     * Get all the perfilAcessos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PerfilAcessoDTO> findAll(Pageable pageable);


    /**
     * Get the "id" perfilAcesso.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PerfilAcessoDTO> findOne(Long id);

    /**
     * Delete the "id" perfilAcesso.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

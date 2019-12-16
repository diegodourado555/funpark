package br.com.service;

import br.com.service.dto.UsuarioPerfilDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.UsuarioPerfil}.
 */
public interface UsuarioPerfilService {

    /**
     * Save a usuarioPerfil.
     *
     * @param usuarioPerfilDTO the entity to save.
     * @return the persisted entity.
     */
    UsuarioPerfilDTO save(UsuarioPerfilDTO usuarioPerfilDTO);

    /**
     * Get all the usuarioPerfils.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<UsuarioPerfilDTO> findAll(Pageable pageable);


    /**
     * Get the "id" usuarioPerfil.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UsuarioPerfilDTO> findOne(Long id);

    /**
     * Delete the "id" usuarioPerfil.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

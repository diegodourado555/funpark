package br.com.service;

import br.com.service.dto.MenuPerfilDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link br.com.domain.MenuPerfil}.
 */
public interface MenuPerfilService {

    /**
     * Save a menuPerfil.
     *
     * @param menuPerfilDTO the entity to save.
     * @return the persisted entity.
     */
    MenuPerfilDTO save(MenuPerfilDTO menuPerfilDTO);

    /**
     * Get all the menuPerfils.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MenuPerfilDTO> findAll(Pageable pageable);


    /**
     * Get the "id" menuPerfil.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MenuPerfilDTO> findOne(Long id);

    /**
     * Delete the "id" menuPerfil.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}

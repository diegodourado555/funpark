package br.com.service.impl;

import br.com.service.MenuPerfilService;
import br.com.domain.MenuPerfil;
import br.com.repository.MenuPerfilRepository;
import br.com.service.dto.MenuPerfilDTO;
import br.com.service.mapper.MenuPerfilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MenuPerfil}.
 */
@Service
@Transactional
public class MenuPerfilServiceImpl implements MenuPerfilService {

    private final Logger log = LoggerFactory.getLogger(MenuPerfilServiceImpl.class);

    private final MenuPerfilRepository menuPerfilRepository;

    private final MenuPerfilMapper menuPerfilMapper;

    public MenuPerfilServiceImpl(MenuPerfilRepository menuPerfilRepository, MenuPerfilMapper menuPerfilMapper) {
        this.menuPerfilRepository = menuPerfilRepository;
        this.menuPerfilMapper = menuPerfilMapper;
    }

    /**
     * Save a menuPerfil.
     *
     * @param menuPerfilDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MenuPerfilDTO save(MenuPerfilDTO menuPerfilDTO) {
        log.debug("Request to save MenuPerfil : {}", menuPerfilDTO);
        MenuPerfil menuPerfil = menuPerfilMapper.toEntity(menuPerfilDTO);
        menuPerfil = menuPerfilRepository.save(menuPerfil);
        return menuPerfilMapper.toDto(menuPerfil);
    }

    /**
     * Get all the menuPerfils.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MenuPerfilDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MenuPerfils");
        return menuPerfilRepository.findAll(pageable)
            .map(menuPerfilMapper::toDto);
    }


    /**
     * Get one menuPerfil by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MenuPerfilDTO> findOne(Long id) {
        log.debug("Request to get MenuPerfil : {}", id);
        return menuPerfilRepository.findById(id)
            .map(menuPerfilMapper::toDto);
    }

    /**
     * Delete the menuPerfil by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MenuPerfil : {}", id);
        menuPerfilRepository.deleteById(id);
    }
}

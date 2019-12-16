package br.com.service.impl;

import br.com.service.UsuarioPerfilService;
import br.com.domain.UsuarioPerfil;
import br.com.repository.UsuarioPerfilRepository;
import br.com.service.dto.UsuarioPerfilDTO;
import br.com.service.mapper.UsuarioPerfilMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link UsuarioPerfil}.
 */
@Service
@Transactional
public class UsuarioPerfilServiceImpl implements UsuarioPerfilService {

    private final Logger log = LoggerFactory.getLogger(UsuarioPerfilServiceImpl.class);

    private final UsuarioPerfilRepository usuarioPerfilRepository;

    private final UsuarioPerfilMapper usuarioPerfilMapper;

    public UsuarioPerfilServiceImpl(UsuarioPerfilRepository usuarioPerfilRepository, UsuarioPerfilMapper usuarioPerfilMapper) {
        this.usuarioPerfilRepository = usuarioPerfilRepository;
        this.usuarioPerfilMapper = usuarioPerfilMapper;
    }

    /**
     * Save a usuarioPerfil.
     *
     * @param usuarioPerfilDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public UsuarioPerfilDTO save(UsuarioPerfilDTO usuarioPerfilDTO) {
        log.debug("Request to save UsuarioPerfil : {}", usuarioPerfilDTO);
        UsuarioPerfil usuarioPerfil = usuarioPerfilMapper.toEntity(usuarioPerfilDTO);
        usuarioPerfil = usuarioPerfilRepository.save(usuarioPerfil);
        return usuarioPerfilMapper.toDto(usuarioPerfil);
    }

    /**
     * Get all the usuarioPerfils.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsuarioPerfilDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UsuarioPerfils");
        return usuarioPerfilRepository.findAll(pageable)
            .map(usuarioPerfilMapper::toDto);
    }


    /**
     * Get one usuarioPerfil by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UsuarioPerfilDTO> findOne(Long id) {
        log.debug("Request to get UsuarioPerfil : {}", id);
        return usuarioPerfilRepository.findById(id)
            .map(usuarioPerfilMapper::toDto);
    }

    /**
     * Delete the usuarioPerfil by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UsuarioPerfil : {}", id);
        usuarioPerfilRepository.deleteById(id);
    }
}

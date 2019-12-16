package br.com.service.impl;

import br.com.service.PerfilAcessoService;
import br.com.domain.PerfilAcesso;
import br.com.repository.PerfilAcessoRepository;
import br.com.service.dto.PerfilAcessoDTO;
import br.com.service.mapper.PerfilAcessoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PerfilAcesso}.
 */
@Service
@Transactional
public class PerfilAcessoServiceImpl implements PerfilAcessoService {

    private final Logger log = LoggerFactory.getLogger(PerfilAcessoServiceImpl.class);

    private final PerfilAcessoRepository perfilAcessoRepository;

    private final PerfilAcessoMapper perfilAcessoMapper;

    public PerfilAcessoServiceImpl(PerfilAcessoRepository perfilAcessoRepository, PerfilAcessoMapper perfilAcessoMapper) {
        this.perfilAcessoRepository = perfilAcessoRepository;
        this.perfilAcessoMapper = perfilAcessoMapper;
    }

    /**
     * Save a perfilAcesso.
     *
     * @param perfilAcessoDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PerfilAcessoDTO save(PerfilAcessoDTO perfilAcessoDTO) {
        log.debug("Request to save PerfilAcesso : {}", perfilAcessoDTO);
        PerfilAcesso perfilAcesso = perfilAcessoMapper.toEntity(perfilAcessoDTO);
        perfilAcesso = perfilAcessoRepository.save(perfilAcesso);
        return perfilAcessoMapper.toDto(perfilAcesso);
    }

    /**
     * Get all the perfilAcessos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PerfilAcessoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PerfilAcessos");
        return perfilAcessoRepository.findAll(pageable)
            .map(perfilAcessoMapper::toDto);
    }


    /**
     * Get one perfilAcesso by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PerfilAcessoDTO> findOne(Long id) {
        log.debug("Request to get PerfilAcesso : {}", id);
        return perfilAcessoRepository.findById(id)
            .map(perfilAcessoMapper::toDto);
    }

    /**
     * Delete the perfilAcesso by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PerfilAcesso : {}", id);
        perfilAcessoRepository.deleteById(id);
    }
}

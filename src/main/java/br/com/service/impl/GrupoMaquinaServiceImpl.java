package br.com.service.impl;

import br.com.service.GrupoMaquinaService;
import br.com.domain.GrupoMaquina;
import br.com.repository.GrupoMaquinaRepository;
import br.com.service.dto.GrupoMaquinaDTO;
import br.com.service.mapper.GrupoMaquinaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link GrupoMaquina}.
 */
@Service
@Transactional
public class GrupoMaquinaServiceImpl implements GrupoMaquinaService {

    private final Logger log = LoggerFactory.getLogger(GrupoMaquinaServiceImpl.class);

    private final GrupoMaquinaRepository grupoMaquinaRepository;

    private final GrupoMaquinaMapper grupoMaquinaMapper;

    public GrupoMaquinaServiceImpl(GrupoMaquinaRepository grupoMaquinaRepository, GrupoMaquinaMapper grupoMaquinaMapper) {
        this.grupoMaquinaRepository = grupoMaquinaRepository;
        this.grupoMaquinaMapper = grupoMaquinaMapper;
    }

    /**
     * Save a grupoMaquina.
     *
     * @param grupoMaquinaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public GrupoMaquinaDTO save(GrupoMaquinaDTO grupoMaquinaDTO) {
        log.debug("Request to save GrupoMaquina : {}", grupoMaquinaDTO);
        GrupoMaquina grupoMaquina = grupoMaquinaMapper.toEntity(grupoMaquinaDTO);
        grupoMaquina = grupoMaquinaRepository.save(grupoMaquina);
        return grupoMaquinaMapper.toDto(grupoMaquina);
    }

    /**
     * Get all the grupoMaquinas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<GrupoMaquinaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all GrupoMaquinas");
        return grupoMaquinaRepository.findAll(pageable)
            .map(grupoMaquinaMapper::toDto);
    }


    /**
     * Get one grupoMaquina by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<GrupoMaquinaDTO> findOne(Long id) {
        log.debug("Request to get GrupoMaquina : {}", id);
        return grupoMaquinaRepository.findById(id)
            .map(grupoMaquinaMapper::toDto);
    }

    /**
     * Delete the grupoMaquina by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete GrupoMaquina : {}", id);
        grupoMaquinaRepository.deleteById(id);
    }
}

package br.com.service.impl;

import br.com.service.LojaMaquinaService;
import br.com.domain.LojaMaquina;
import br.com.repository.LojaMaquinaRepository;
import br.com.service.dto.LojaMaquinaDTO;
import br.com.service.mapper.LojaMaquinaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link LojaMaquina}.
 */
@Service
@Transactional
public class LojaMaquinaServiceImpl implements LojaMaquinaService {

    private final Logger log = LoggerFactory.getLogger(LojaMaquinaServiceImpl.class);

    private final LojaMaquinaRepository lojaMaquinaRepository;

    private final LojaMaquinaMapper lojaMaquinaMapper;

    public LojaMaquinaServiceImpl(LojaMaquinaRepository lojaMaquinaRepository, LojaMaquinaMapper lojaMaquinaMapper) {
        this.lojaMaquinaRepository = lojaMaquinaRepository;
        this.lojaMaquinaMapper = lojaMaquinaMapper;
    }

    /**
     * Save a lojaMaquina.
     *
     * @param lojaMaquinaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LojaMaquinaDTO save(LojaMaquinaDTO lojaMaquinaDTO) {
        log.debug("Request to save LojaMaquina : {}", lojaMaquinaDTO);
        LojaMaquina lojaMaquina = lojaMaquinaMapper.toEntity(lojaMaquinaDTO);
        lojaMaquina = lojaMaquinaRepository.save(lojaMaquina);
        return lojaMaquinaMapper.toDto(lojaMaquina);
    }

    /**
     * Get all the lojaMaquinas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LojaMaquinaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all LojaMaquinas");
        return lojaMaquinaRepository.findAll(pageable)
            .map(lojaMaquinaMapper::toDto);
    }


    /**
     * Get one lojaMaquina by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LojaMaquinaDTO> findOne(Long id) {
        log.debug("Request to get LojaMaquina : {}", id);
        return lojaMaquinaRepository.findById(id)
            .map(lojaMaquinaMapper::toDto);
    }

    /**
     * Delete the lojaMaquina by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete LojaMaquina : {}", id);
        lojaMaquinaRepository.deleteById(id);
    }
}

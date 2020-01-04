package br.com.service.impl;

import br.com.service.HistoricoMaquinaService;
import br.com.domain.HistoricoMaquina;
import br.com.repository.HistoricoMaquinaRepository;
import br.com.service.dto.HistoricoMaquinaDTO;
import br.com.service.mapper.HistoricoMaquinaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HistoricoMaquina}.
 */
@Service
@Transactional
public class HistoricoMaquinaServiceImpl implements HistoricoMaquinaService {

    private final Logger log = LoggerFactory.getLogger(HistoricoMaquinaServiceImpl.class);

    private final HistoricoMaquinaRepository historicoMaquinaRepository;

    private final HistoricoMaquinaMapper historicoMaquinaMapper;

    public HistoricoMaquinaServiceImpl(HistoricoMaquinaRepository historicoMaquinaRepository, HistoricoMaquinaMapper historicoMaquinaMapper) {
        this.historicoMaquinaRepository = historicoMaquinaRepository;
        this.historicoMaquinaMapper = historicoMaquinaMapper;
    }

    /**
     * Save a historicoMaquina.
     *
     * @param historicoMaquinaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HistoricoMaquinaDTO save(HistoricoMaquinaDTO historicoMaquinaDTO) {
        log.debug("Request to save HistoricoMaquina : {}", historicoMaquinaDTO);
        HistoricoMaquina historicoMaquina = historicoMaquinaMapper.toEntity(historicoMaquinaDTO);
        historicoMaquina = historicoMaquinaRepository.save(historicoMaquina);
        return historicoMaquinaMapper.toDto(historicoMaquina);
    }

    /**
     * Get all the historicoMaquinas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HistoricoMaquinaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HistoricoMaquinas");
        return historicoMaquinaRepository.findAll(pageable)
            .map(historicoMaquinaMapper::toDto);
    }


    /**
     * Get one historicoMaquina by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HistoricoMaquinaDTO> findOne(Long id) {
        log.debug("Request to get HistoricoMaquina : {}", id);
        return historicoMaquinaRepository.findById(id)
            .map(historicoMaquinaMapper::toDto);
    }

    /**
     * Delete the historicoMaquina by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HistoricoMaquina : {}", id);
        historicoMaquinaRepository.deleteById(id);
    }
}

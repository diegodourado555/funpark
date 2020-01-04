package br.com.service.impl;

import br.com.service.HistoricoOperadorCaixaService;
import br.com.domain.HistoricoOperadorCaixa;
import br.com.repository.HistoricoOperadorCaixaRepository;
import br.com.service.dto.HistoricoOperadorCaixaDTO;
import br.com.service.mapper.HistoricoOperadorCaixaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link HistoricoOperadorCaixa}.
 */
@Service
@Transactional
public class HistoricoOperadorCaixaServiceImpl implements HistoricoOperadorCaixaService {

    private final Logger log = LoggerFactory.getLogger(HistoricoOperadorCaixaServiceImpl.class);

    private final HistoricoOperadorCaixaRepository historicoOperadorCaixaRepository;

    private final HistoricoOperadorCaixaMapper historicoOperadorCaixaMapper;

    public HistoricoOperadorCaixaServiceImpl(HistoricoOperadorCaixaRepository historicoOperadorCaixaRepository, HistoricoOperadorCaixaMapper historicoOperadorCaixaMapper) {
        this.historicoOperadorCaixaRepository = historicoOperadorCaixaRepository;
        this.historicoOperadorCaixaMapper = historicoOperadorCaixaMapper;
    }

    /**
     * Save a historicoOperadorCaixa.
     *
     * @param historicoOperadorCaixaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public HistoricoOperadorCaixaDTO save(HistoricoOperadorCaixaDTO historicoOperadorCaixaDTO) {
        log.debug("Request to save HistoricoOperadorCaixa : {}", historicoOperadorCaixaDTO);
        HistoricoOperadorCaixa historicoOperadorCaixa = historicoOperadorCaixaMapper.toEntity(historicoOperadorCaixaDTO);
        historicoOperadorCaixa = historicoOperadorCaixaRepository.save(historicoOperadorCaixa);
        return historicoOperadorCaixaMapper.toDto(historicoOperadorCaixa);
    }

    /**
     * Get all the historicoOperadorCaixas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HistoricoOperadorCaixaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HistoricoOperadorCaixas");
        return historicoOperadorCaixaRepository.findAll(pageable)
            .map(historicoOperadorCaixaMapper::toDto);
    }


    /**
     * Get one historicoOperadorCaixa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HistoricoOperadorCaixaDTO> findOne(Long id) {
        log.debug("Request to get HistoricoOperadorCaixa : {}", id);
        return historicoOperadorCaixaRepository.findById(id)
            .map(historicoOperadorCaixaMapper::toDto);
    }

    /**
     * Delete the historicoOperadorCaixa by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HistoricoOperadorCaixa : {}", id);
        historicoOperadorCaixaRepository.deleteById(id);
    }
}

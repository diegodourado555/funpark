package br.com.service.impl;

import br.com.service.OperadorCaixaService;
import br.com.domain.OperadorCaixa;
import br.com.repository.OperadorCaixaRepository;
import br.com.service.dto.OperadorCaixaDTO;
import br.com.service.mapper.OperadorCaixaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link OperadorCaixa}.
 */
@Service
@Transactional
public class OperadorCaixaServiceImpl implements OperadorCaixaService {

    private final Logger log = LoggerFactory.getLogger(OperadorCaixaServiceImpl.class);

    private final OperadorCaixaRepository operadorCaixaRepository;

    private final OperadorCaixaMapper operadorCaixaMapper;

    public OperadorCaixaServiceImpl(OperadorCaixaRepository operadorCaixaRepository, OperadorCaixaMapper operadorCaixaMapper) {
        this.operadorCaixaRepository = operadorCaixaRepository;
        this.operadorCaixaMapper = operadorCaixaMapper;
    }

    /**
     * Save a operadorCaixa.
     *
     * @param operadorCaixaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public OperadorCaixaDTO save(OperadorCaixaDTO operadorCaixaDTO) {
        log.debug("Request to save OperadorCaixa : {}", operadorCaixaDTO);
        OperadorCaixa operadorCaixa = operadorCaixaMapper.toEntity(operadorCaixaDTO);
        operadorCaixa = operadorCaixaRepository.save(operadorCaixa);
        return operadorCaixaMapper.toDto(operadorCaixa);
    }

    /**
     * Get all the operadorCaixas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<OperadorCaixaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all OperadorCaixas");
        List<OperadorCaixaDTO> operadores = operadorCaixaRepository.findAllWithStoreDescription();
        return new PageImpl<OperadorCaixaDTO>(operadores);
    }


    /**
     * Get one operadorCaixa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OperadorCaixaDTO> findOne(Long id) {
        log.debug("Request to get OperadorCaixa : {}", id);
        return operadorCaixaRepository.findById(id)
            .map(operadorCaixaMapper::toDto);
    }

    /**
     * Delete the operadorCaixa by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OperadorCaixa : {}", id);
        operadorCaixaRepository.deleteById(id);
    }
}

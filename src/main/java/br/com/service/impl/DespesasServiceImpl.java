package br.com.service.impl;

import br.com.service.DespesasService;
import br.com.domain.Despesas;
import br.com.repository.DespesasRepository;
import br.com.service.dto.DespesasDTO;
import br.com.service.mapper.DespesasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Despesas}.
 */
@Service
@Transactional
public class DespesasServiceImpl implements DespesasService {

    private final Logger log = LoggerFactory.getLogger(DespesasServiceImpl.class);

    private final DespesasRepository despesasRepository;

    private final DespesasMapper despesasMapper;

    public DespesasServiceImpl(DespesasRepository despesasRepository, DespesasMapper despesasMapper) {
        this.despesasRepository = despesasRepository;
        this.despesasMapper = despesasMapper;
    }

    /**
     * Save a despesas.
     *
     * @param despesasDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DespesasDTO save(DespesasDTO despesasDTO) {
        log.debug("Request to save Despesas : {}", despesasDTO);
        Despesas despesas = despesasMapper.toEntity(despesasDTO);
        despesas = despesasRepository.save(despesas);
        return despesasMapper.toDto(despesas);
    }

    /**
     * Get all the despesas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DespesasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Despesas");
        return despesasRepository.findAll(pageable)
            .map(despesasMapper::toDto);
    }


    /**
     * Get one despesas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DespesasDTO> findOne(Long id) {
        log.debug("Request to get Despesas : {}", id);
        return despesasRepository.findById(id)
            .map(despesasMapper::toDto);
    }

    /**
     * Delete the despesas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Despesas : {}", id);
        despesasRepository.deleteById(id);
    }
}

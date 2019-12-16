package br.com.service.impl;

import br.com.service.LojaService;
import br.com.domain.Loja;
import br.com.repository.LojaRepository;
import br.com.service.dto.LojaDTO;
import br.com.service.mapper.LojaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Loja}.
 */
@Service
@Transactional
public class LojaServiceImpl implements LojaService {

    private final Logger log = LoggerFactory.getLogger(LojaServiceImpl.class);

    private final LojaRepository lojaRepository;

    private final LojaMapper lojaMapper;

    public LojaServiceImpl(LojaRepository lojaRepository, LojaMapper lojaMapper) {
        this.lojaRepository = lojaRepository;
        this.lojaMapper = lojaMapper;
    }

    /**
     * Save a loja.
     *
     * @param lojaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public LojaDTO save(LojaDTO lojaDTO) {
        log.debug("Request to save Loja : {}", lojaDTO);
        Loja loja = lojaMapper.toEntity(lojaDTO);
        loja = lojaRepository.save(loja);
        return lojaMapper.toDto(loja);
    }

    /**
     * Get all the lojas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<LojaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Lojas");
        return lojaRepository.findAll(pageable)
            .map(lojaMapper::toDto);
    }


    /**
     * Get one loja by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<LojaDTO> findOne(Long id) {
        log.debug("Request to get Loja : {}", id);
        return lojaRepository.findById(id)
            .map(lojaMapper::toDto);
    }

    /**
     * Delete the loja by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Loja : {}", id);
        lojaRepository.deleteById(id);
    }
}

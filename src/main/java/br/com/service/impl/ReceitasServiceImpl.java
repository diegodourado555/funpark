package br.com.service.impl;

import br.com.service.ReceitasService;
import br.com.domain.Receitas;
import br.com.repository.ReceitasRepository;
import br.com.service.dto.ReceitasDTO;
import br.com.service.mapper.ReceitasMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Receitas}.
 */
@Service
@Transactional
public class ReceitasServiceImpl implements ReceitasService {

    private final Logger log = LoggerFactory.getLogger(ReceitasServiceImpl.class);

    private final ReceitasRepository receitasRepository;

    private final ReceitasMapper receitasMapper;

    public ReceitasServiceImpl(ReceitasRepository receitasRepository, ReceitasMapper receitasMapper) {
        this.receitasRepository = receitasRepository;
        this.receitasMapper = receitasMapper;
    }

    /**
     * Save a receitas.
     *
     * @param receitasDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ReceitasDTO save(ReceitasDTO receitasDTO) {
        log.debug("Request to save Receitas : {}", receitasDTO);
        Receitas receitas = receitasMapper.toEntity(receitasDTO);
        receitas = receitasRepository.save(receitas);
        return receitasMapper.toDto(receitas);
    }

    /**
     * Get all the receitas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReceitasDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Receitas");
        return receitasRepository.findAll(pageable)
            .map(receitasMapper::toDto);
    }


    /**
     * Get one receitas by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReceitasDTO> findOne(Long id) {
        log.debug("Request to get Receitas : {}", id);
        return receitasRepository.findById(id)
            .map(receitasMapper::toDto);
    }

    /**
     * Delete the receitas by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Receitas : {}", id);
        receitasRepository.deleteById(id);
    }
}

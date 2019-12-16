package br.com.service.impl;

import br.com.service.FunparkService;
import br.com.domain.Funpark;
import br.com.repository.FunparkRepository;
import br.com.service.dto.FunparkDTO;
import br.com.service.mapper.FunparkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Funpark}.
 */
@Service
@Transactional
public class FunparkServiceImpl implements FunparkService {

    private final Logger log = LoggerFactory.getLogger(FunparkServiceImpl.class);

    private final FunparkRepository funparkRepository;

    private final FunparkMapper funparkMapper;

    public FunparkServiceImpl(FunparkRepository funparkRepository, FunparkMapper funparkMapper) {
        this.funparkRepository = funparkRepository;
        this.funparkMapper = funparkMapper;
    }

    /**
     * Save a funpark.
     *
     * @param funparkDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public FunparkDTO save(FunparkDTO funparkDTO) {
        log.debug("Request to save Funpark : {}", funparkDTO);
        Funpark funpark = funparkMapper.toEntity(funparkDTO);
        funpark = funparkRepository.save(funpark);
        return funparkMapper.toDto(funpark);
    }

    /**
     * Get all the funparks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FunparkDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Funparks");
        return funparkRepository.findAll(pageable)
            .map(funparkMapper::toDto);
    }


    /**
     * Get one funpark by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FunparkDTO> findOne(Long id) {
        log.debug("Request to get Funpark : {}", id);
        return funparkRepository.findById(id)
            .map(funparkMapper::toDto);
    }

    /**
     * Delete the funpark by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Funpark : {}", id);
        funparkRepository.deleteById(id);
    }
}

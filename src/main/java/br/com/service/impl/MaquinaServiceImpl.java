package br.com.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.domain.Maquina;
import br.com.repository.MaquinaRepository;
import br.com.service.MaquinaService;
import br.com.service.dto.MaquinaDTO;
import br.com.service.mapper.MaquinaMapper;

/**
 * Service Implementation for managing {@link Maquina}.
 */
@Service
@Transactional
public class MaquinaServiceImpl implements MaquinaService {

    private final Logger log = LoggerFactory.getLogger(MaquinaServiceImpl.class);

    private final MaquinaRepository maquinaRepository;

    private final MaquinaMapper maquinaMapper;

    public MaquinaServiceImpl(MaquinaRepository maquinaRepository, MaquinaMapper maquinaMapper) {
        this.maquinaRepository = maquinaRepository;
        this.maquinaMapper = maquinaMapper;
    }

    /**
     * Save a maquina.
     *
     * @param maquinaDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MaquinaDTO save(MaquinaDTO maquinaDTO) {
        log.debug("Request to save Maquina : {}", maquinaDTO);
        Maquina maquina = maquinaMapper.toEntity(maquinaDTO);
        maquina = maquinaRepository.save(maquina);
        return maquinaMapper.toDto(maquina);
    }

    /**
     * Get all the maquinas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MaquinaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Maquinas with group description");
        List<MaquinaDTO> maquinas = maquinaRepository.findAllWithGroupDescription();
        return new PageImpl<MaquinaDTO>(maquinas);
    }


    /**
     * Get one maquina by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MaquinaDTO> findOne(Long id) {
        log.debug("Request to get Maquina : {}", id);
        return maquinaRepository.findById(id)
            .map(maquinaMapper::toDto);
    }

    /**
     * Delete the maquina by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Maquina : {}", id);
        maquinaRepository.deleteById(id);
    }
}

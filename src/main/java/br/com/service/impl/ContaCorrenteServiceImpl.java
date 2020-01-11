package br.com.service.impl;

import br.com.service.ContaCorrenteService;
import br.com.domain.ContaCorrente;
import br.com.repository.ContaCorrenteRepository;
import br.com.service.dto.ContaCorrenteDTO;
import br.com.service.mapper.ContaCorrenteMapper;
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
 * Service Implementation for managing {@link ContaCorrente}.
 */
@Service
@Transactional
public class ContaCorrenteServiceImpl implements ContaCorrenteService {

    private final Logger log = LoggerFactory.getLogger(ContaCorrenteServiceImpl.class);

    private final ContaCorrenteRepository contaCorrenteRepository;

    private final ContaCorrenteMapper contaCorrenteMapper;

    public ContaCorrenteServiceImpl(ContaCorrenteRepository contaCorrenteRepository, ContaCorrenteMapper contaCorrenteMapper) {
        this.contaCorrenteRepository = contaCorrenteRepository;
        this.contaCorrenteMapper = contaCorrenteMapper;
    }

    /**
     * Save a contaCorrente.
     *
     * @param contaCorrenteDTO the entity to save.
     * @return the persisted entity.
     */
    @Override
    public ContaCorrenteDTO save(ContaCorrenteDTO contaCorrenteDTO) {
        log.debug("Request to save ContaCorrente : {}", contaCorrenteDTO);
        ContaCorrente contaCorrente = contaCorrenteMapper.toEntity(contaCorrenteDTO);
        contaCorrente = contaCorrenteRepository.save(contaCorrente);
        return contaCorrenteMapper.toDto(contaCorrente);
    }

    /**
     * Get all the contaCorrentes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContaCorrenteDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ContaCorrentes");
        return contaCorrenteRepository.findAll(pageable)
            .map(contaCorrenteMapper::toDto);
    }

    /**
     * Get all the contaCorrentes accordingly with filter.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ContaCorrenteDTO> search(String descricao) {
    	log.debug("Request to get all ContaCorrentes accordingly with filter");
    	List<ContaCorrente> listaContaCorrente = contaCorrenteRepository.search(descricao);
    	return new PageImpl<ContaCorrenteDTO>(contaCorrenteMapper.toDtoList(listaContaCorrente));
    }


    /**
     * Get one contaCorrente by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ContaCorrenteDTO> findOne(Long id) {
        log.debug("Request to get ContaCorrente : {}", id);
        return contaCorrenteRepository.findById(id)
            .map(contaCorrenteMapper::toDto);
    }

    /**
     * Delete the contaCorrente by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ContaCorrente : {}", id);
        contaCorrenteRepository.deleteById(id);
    }
}

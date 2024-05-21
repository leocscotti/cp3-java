package br.com.fiap.revisaoapi.service;

import br.com.fiap.revisaoapi.dto.RoupaDTO;
import br.com.fiap.revisaoapi.model.Roupa;
import br.com.fiap.revisaoapi.repository.RoupaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoupaService {
    private final RoupaRepository roupaRepository;
    private static final Pageable customPageable = PageRequest.of(0, 5, Sort.by("tipo").descending());

    @Autowired
    public RoupaService(RoupaRepository roupaRepository) {
        this.roupaRepository = roupaRepository;
    }

    public Page<RoupaDTO> findAll() {
        return roupaRepository.findAll(customPageable).map(this::toDTO);
    }

    public RoupaDTO findById(Long id) {
        Optional<Roupa> roupa = roupaRepository.findById(id);
        return roupa.map(this::toDTO).orElse(null);
    }

    public Roupa save(Roupa roupa) {
        return roupaRepository.save(roupa);
    }

    public Roupa update(Long id, Roupa roupa) {
        Optional<Roupa> roupaOptional = roupaRepository.findById(id);
        if (roupaOptional.isPresent()) {
            Roupa roupaUpdate = roupaOptional.get();
            roupaUpdate.setTipo(roupa.getTipo());
            roupaUpdate.setTamanho(roupa.getTamanho());
            roupaUpdate.setCor(roupa.getCor());
            roupaUpdate.setMarca(roupa.getMarca());
            roupaUpdate.setPreco(roupa.getPreco());
            roupa = roupaRepository.save(roupaUpdate);
            return roupa;
        }
        return null;
    }

    public void delete(Long id) {
        Optional<Roupa> roupaOptional = roupaRepository.findById(id);
        roupaOptional.ifPresent(roupaRepository::delete);
    }

    private RoupaDTO toDTO(Roupa roupa) {
        RoupaDTO roupaDTO = new RoupaDTO();
        roupaDTO.setId(roupa.getId());
        roupaDTO.setTipo(roupa.getTipo());
        roupaDTO.setTamanho(roupa.getTamanho());
        roupaDTO.setCor(roupa.getCor());
        roupaDTO.setPreco(roupa.getPreco());
        return roupaDTO;
    }
}

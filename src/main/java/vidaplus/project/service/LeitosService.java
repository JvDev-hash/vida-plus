package vidaplus.project.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import vidaplus.project.DTO.LeitoDTO;
import vidaplus.project.model.Leito;
import vidaplus.project.repository.LeitosRepository;

@Service
public class LeitosService {

    private final LeitosRepository leitoRepository;

    public LeitosService(LeitosRepository leitoRepository) {
        this.leitoRepository = leitoRepository;
    }

    public void cadastrarLeito(LeitoDTO leitoDTO) {
        var leito = new Leito();
        leito.setNumero(leitoDTO.getNumero());
        leito.setDescricao(leitoDTO.getDescricao());
        leito.setStatus(leitoDTO.getStatus());
        leito.setTipo(leitoDTO.getTipo());
        leito.setAlaMedica(leitoDTO.getAlaMedica());
        leitoRepository.save(leito);
    }

    public void editarLeito(Long leitoId, LeitoDTO leitoDTO) {
        var leito = leitoRepository.findById(leitoId).orElseThrow(() -> new EntityNotFoundException("Leito não encontrado"));
        leito.setNumero(leitoDTO.getNumero());
        leito.setDescricao(leitoDTO.getDescricao());
        leito.setStatus(leitoDTO.getStatus());
        leito.setTipo(leitoDTO.getTipo());
        leito.setAlaMedica(leitoDTO.getAlaMedica());
        leitoRepository.save(leito);
    }

    public void mudarStatusLeito(Long leitoId, String status) {
        var leito = leitoRepository.findById(leitoId).orElseThrow(() -> new EntityNotFoundException("Leito não encontrado"));
        leito.setStatus(status);
        leitoRepository.save(leito);
    }

    public Page<Leito> listarLeitos(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "leitoId"));
        Page<Leito> leitos = leitoRepository.findAll(pageable);
        return new PageImpl<>(leitos.getContent(), pageable, leitos.getTotalElements());
    }
}

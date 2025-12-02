package vidaplus.project.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import vidaplus.project.DTO.SuprimentoDTO;
import vidaplus.project.model.Suprimento;
import vidaplus.project.repository.SuprimentoRepository;

@Service
public class SuprimentosService {

    private final SuprimentoRepository suprimentoRepository;

    public SuprimentosService(SuprimentoRepository suprimentoRepository) {
        this.suprimentoRepository = suprimentoRepository;
    }

    public void cadastrarSuprimento(SuprimentoDTO suprimentoDTO) {
        var suprimento = new Suprimento();
        suprimento.setNome(suprimentoDTO.getNome());
        suprimento.setDescricao(suprimentoDTO.getDescricao());
        suprimento.setQuantidade(suprimentoDTO.getQuantidade());
        suprimento.setQuantidadeMinima(suprimentoDTO.getQuantidadeMinima());
        suprimento.setUnidade(suprimentoDTO.getUnidade());
        suprimento.setStatus(suprimentoDTO.getStatus());
        suprimento.setStatus("Ativo");
        suprimento.setDataCriacao(new Date());
        suprimento.setDataAtualizacao(new Date());

        suprimentoRepository.save(suprimento);
    }

    public Page<Suprimento> listarSuprimentos(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "suprimentoId"));
        Page<Suprimento> suprimentos = suprimentoRepository.findAll(pageable);
        return new PageImpl<>(suprimentos.getContent(), pageable, suprimentos.getTotalElements());
    }

    public void atualizarSuprimento(Long suprimentoId, SuprimentoDTO suprimentoDTO) {
        var suprimento = suprimentoRepository.findById(suprimentoId).orElseThrow(() -> new EntityNotFoundException("Suprimento não encontrado"));
        suprimento.setNome(suprimentoDTO.getNome());
        suprimento.setDescricao(suprimentoDTO.getDescricao());
        suprimento.setQuantidade(suprimentoDTO.getQuantidade());
        suprimento.setQuantidadeMinima(suprimentoDTO.getQuantidadeMinima());
        suprimento.setUnidade(suprimentoDTO.getUnidade());
        suprimento.setStatus(suprimentoDTO.getStatus());
        suprimento.setDataAtualizacao(new Date());
        suprimentoRepository.save(suprimento);
    }
}

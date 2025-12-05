package vidaplus.project.service;

import vidaplus.project.DTO.ProfissionalDTO;
import vidaplus.project.model.Endereco;
import vidaplus.project.model.Profissional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import vidaplus.project.repository.ProfissionalRepository;
import vidaplus.project.repository.EnderecoRepository;

@Service
public class PessoasService {

    private final ProfissionalRepository profissionalRepository;
    private final EnderecoRepository enderecoRepository;
    public PessoasService(ProfissionalRepository profissionalRepository, EnderecoRepository enderecoRepository) {
        this.profissionalRepository = profissionalRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public void cadastrarProfissional(ProfissionalDTO profissionalDTO) {
        var profissional = new Profissional();
        profissional.setNome(profissionalDTO.getNome());
        profissional.setCpf(profissionalDTO.getCpf());
        profissional.setTelefone(profissionalDTO.getTelefone());
        profissional.setEmail(profissionalDTO.getEmail());
        profissional.setDataNascimento(profissionalDTO.getDataNascimento());
        profissional.setEspecialidade(profissionalDTO.getEspecialidade());
        profissional.setCrm(profissionalDTO.getCrm());
        profissional.setStatus(profissionalDTO.getStatus());

        var endereco = new Endereco();
        endereco.setLogradouro(profissionalDTO.getEndereco().getLogradouro());
        endereco.setNumero(profissionalDTO.getEndereco().getNumero());
        endereco.setComplemento(profissionalDTO.getEndereco().getComplemento());
        endereco.setBairro(profissionalDTO.getEndereco().getBairro());
        endereco.setCidade(profissionalDTO.getEndereco().getCidade());
        endereco.setUf(profissionalDTO.getEndereco().getUf());
        endereco.setCep(profissionalDTO.getEndereco().getCep());

        profissional.setEndereco(enderecoRepository.save(endereco));

        profissionalRepository.save(profissional);
    }

    public void editarProfissional(Long id, ProfissionalDTO profissionalDTO) {
        var profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));
        profissional.setNome(profissionalDTO.getNome());
        profissional.setCpf(profissionalDTO.getCpf());
        profissional.setTelefone(profissionalDTO.getTelefone());
        profissional.setEmail(profissionalDTO.getEmail());
        profissional.setDataNascimento(profissionalDTO.getDataNascimento());
        profissional.setEspecialidade(profissionalDTO.getEspecialidade());
        profissional.setCrm(profissionalDTO.getCrm());
        profissional.setStatus(profissionalDTO.getStatus());

        var endereco = new Endereco();
        endereco.setLogradouro(profissionalDTO.getEndereco().getLogradouro());
        endereco.setNumero(profissionalDTO.getEndereco().getNumero());
        endereco.setComplemento(profissionalDTO.getEndereco().getComplemento());
        endereco.setBairro(profissionalDTO.getEndereco().getBairro());
        endereco.setCidade(profissionalDTO.getEndereco().getCidade());
        endereco.setUf(profissionalDTO.getEndereco().getUf());
        endereco.setCep(profissionalDTO.getEndereco().getCep());

        profissional.setEndereco(enderecoRepository.save(endereco));

        profissionalRepository.save(profissional);
    }

    public void mudarStatusProfissional(Long id, String status) {
        var profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));
        profissional.setStatus(status);
        profissionalRepository.save(profissional);
    }

    public Page<Profissional> listarProfissionais(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "profissionalId"));
        Page<Profissional> profissionais = profissionalRepository.findAll(pageable);
        return new PageImpl<>(profissionais.getContent(), pageable, profissionais.getTotalElements());
    }
}

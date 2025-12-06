package vidaplus.project.service;

import vidaplus.project.DTO.ProfissionalDTO;
import vidaplus.project.DTO.ResponseDTO.PacienteResponseDTO;
import vidaplus.project.DTO.ResponseDTO.ProfissionalResponseDTO;
import vidaplus.project.model.Endereco;
import vidaplus.project.model.Profissional;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityNotFoundException;
import vidaplus.project.repository.ProfissionalRepository;
import vidaplus.project.repository.EnderecoRepository;
import vidaplus.project.repository.PacienteRepository;
import vidaplus.project.DTO.PacienteDTO;
import vidaplus.project.model.Paciente;

@Service
public class PessoasService {

    private final ProfissionalRepository profissionalRepository;
    private final EnderecoRepository enderecoRepository;
    private final PacienteRepository pacienteRepository;

    public PessoasService(ProfissionalRepository profissionalRepository, EnderecoRepository enderecoRepository, PacienteRepository pacienteRepository) {
        this.profissionalRepository = profissionalRepository;
        this.enderecoRepository = enderecoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    // Profissionais

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

    public Page<ProfissionalResponseDTO> listarProfissionais(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "profissionalId"));
        Page<Profissional> profissionais = profissionalRepository.findAll(pageable);
        return new PageImpl<>(profissionais.getContent().stream()
        .map(profissional -> new ProfissionalResponseDTO(profissional.getNome(), profissional.getCpf(), profissional.getTelefone(), profissional.getEmail(), profissional.getDataNascimento(), profissional.getEspecialidade(), profissional.getCrm(), profissional.getStatus()))
        .collect(Collectors.toList()), pageable, profissionais.getTotalElements());
    }

    public Profissional buscarProfissionalPorId(Long id) {
        var profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));
        return profissional;
    }

    public void deletarProfissional(Long id) {
        var profissional = profissionalRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado"));
        profissionalRepository.delete(profissional);
    }

    // Pacientes

    public void cadastrarPaciente(PacienteDTO pacienteDTO) {
        var paciente = new Paciente();
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setSexo(pacienteDTO.getSexo());
        paciente.setEscolaridade(pacienteDTO.getEscolaridade());
        paciente.setOcupacao(pacienteDTO.getOcupacao());

        var endereco = new Endereco();
        endereco.setLogradouro(pacienteDTO.getEndereco().getLogradouro());
        endereco.setNumero(pacienteDTO.getEndereco().getNumero());
        endereco.setComplemento(pacienteDTO.getEndereco().getComplemento());
        endereco.setBairro(pacienteDTO.getEndereco().getBairro());
        endereco.setCidade(pacienteDTO.getEndereco().getCidade());
        endereco.setUf(pacienteDTO.getEndereco().getUf());
        endereco.setCep(pacienteDTO.getEndereco().getCep());

        paciente.setEndereco(enderecoRepository.save(endereco));

        pacienteRepository.save(paciente);
    }
    
    public void editarPaciente(Long id, PacienteDTO pacienteDTO) {
        var paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
        paciente.setNome(pacienteDTO.getNome());
        paciente.setCpf(pacienteDTO.getCpf());
        paciente.setTelefone(pacienteDTO.getTelefone());
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setDataNascimento(pacienteDTO.getDataNascimento());
        paciente.setSexo(pacienteDTO.getSexo());
        paciente.setEscolaridade(pacienteDTO.getEscolaridade());
        paciente.setOcupacao(pacienteDTO.getOcupacao());

        var endereco = new Endereco();
        endereco.setLogradouro(pacienteDTO.getEndereco().getLogradouro());
        endereco.setNumero(pacienteDTO.getEndereco().getNumero());
        endereco.setComplemento(pacienteDTO.getEndereco().getComplemento());
        endereco.setBairro(pacienteDTO.getEndereco().getBairro());
        endereco.setCidade(pacienteDTO.getEndereco().getCidade());
        endereco.setUf(pacienteDTO.getEndereco().getUf());
        endereco.setCep(pacienteDTO.getEndereco().getCep());

        paciente.setEndereco(enderecoRepository.save(endereco));

        pacienteRepository.save(paciente);
    }
    
    public Page<PacienteResponseDTO> listarPacientes(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "pacienteId"));
        Page<Paciente> pacientes = pacienteRepository.findAll(pageable);
        return new PageImpl<>(pacientes.getContent().stream()
        .map(paciente -> new PacienteResponseDTO(paciente.getNome(), paciente.getCpf(), paciente.getTelefone(), paciente.getEmail(), paciente.getDataNascimento(), paciente.getSexo(), paciente.getEscolaridade(), paciente.getOcupacao()))
        .collect(Collectors.toList()), pageable, pacientes.getTotalElements());
    }

    public Paciente buscarPacientePorId(Long id) {
        var paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
        return paciente;
    }

    public void deletarPaciente(Long id) {
        var paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado"));
        pacienteRepository.delete(paciente);
    }
    
}

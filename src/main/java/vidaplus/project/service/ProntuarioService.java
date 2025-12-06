package vidaplus.project.service;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import vidaplus.project.repository.ProntuarioRepository;
import vidaplus.project.repository.PacienteRepository;
import vidaplus.project.repository.ProfissionalRepository;
import vidaplus.project.DTO.ProntuarioDTO;
import vidaplus.project.DTO.ResponseDTO.PacienteResponseDTO;
import vidaplus.project.DTO.ResponseDTO.ProfissionalResponseDTO;
import vidaplus.project.DTO.ResponseDTO.ProntuarioResponseDTO;
import vidaplus.project.model.Prontuario;
import vidaplus.project.model.Paciente;
import vidaplus.project.model.Profissional;
import vidaplus.project.repository.LeitosRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final ProfissionalRepository profissionalRepository;
    private final LeitosRepository leitosRepository;
    private final PacienteRepository pacienteRepository;

    public ProntuarioService(ProntuarioRepository prontuarioRepository, ProfissionalRepository profissionalRepository, LeitosRepository leitosRepository, PacienteRepository pacienteRepository) {
        this.prontuarioRepository = prontuarioRepository;
        this.profissionalRepository = profissionalRepository;
        this.leitosRepository = leitosRepository;
        this.pacienteRepository = pacienteRepository;
    }

    public void admissaoPaciente(ProntuarioDTO prontuarioDTO) {
        var prontuario = new Prontuario();
        prontuario.setNumeroProntuario("PR-" + UUID.randomUUID().toString().substring(0, 8));
        prontuario.setDataAdmissao(prontuarioDTO.getDataAdmissao());
        prontuario.setMotivoAdmissao(prontuarioDTO.getMotivoAdmissao());
        prontuario.setDiagnostico(prontuarioDTO.getDiagnostico());
        prontuario.setHistoricoMedico(prontuarioDTO.getHistoricoMedico());
        prontuario.setEvolucao(prontuarioDTO.getEvolucao());
        prontuario.setEncaminhamento(prontuarioDTO.getEncaminhamento());
        prontuario.setConduta(prontuarioDTO.getConduta());
        prontuario.setPaciente(pacienteRepository.findById(prontuarioDTO.getPacienteId()).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado")));
        prontuario.setProfissional(profissionalRepository.findById(prontuarioDTO.getProfissionalId()).orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado")));
        prontuarioRepository.save(prontuario);
    }

    public void internacaoPaciente(Long prontuarioId, String motivoInternacao, Date dataInternacao, Long leitoId) {
        var prontuario = prontuarioRepository.findById(prontuarioId).orElseThrow(() -> new EntityNotFoundException("Prontuario não encontrado"));
        prontuario.setDataInternacao(dataInternacao);
        prontuario.setMotivoInternacao(motivoInternacao);
        var leito = leitosRepository.findById(leitoId).orElseThrow(() -> new EntityNotFoundException("Leito não encontrado"));
        leito.setStatus("OCUPADO");
        prontuario.setLeito(leitosRepository.save(leito));
        prontuarioRepository.save(prontuario);
    }

    public void editarProntuario(Long prontuarioId, ProntuarioDTO prontuarioDTO) {
        var prontuario = prontuarioRepository.findById(prontuarioId).orElseThrow(() -> new EntityNotFoundException("Prontuario não encontrado"));
        prontuario.setMotivoAdmissao(prontuarioDTO.getMotivoAdmissao());
        prontuario.setDiagnostico(prontuarioDTO.getDiagnostico());
        prontuario.setHistoricoMedico(prontuarioDTO.getHistoricoMedico());
        prontuario.setEvolucao(prontuarioDTO.getEvolucao());
        prontuario.setEncaminhamento(prontuarioDTO.getEncaminhamento());
        prontuario.setConduta(prontuarioDTO.getConduta());
        prontuario.setPaciente(pacienteRepository.findById(prontuarioDTO.getPacienteId()).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado")));
        prontuario.setProfissional(profissionalRepository.findById(prontuarioDTO.getProfissionalId()).orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado")));
        prontuarioRepository.save(prontuario);
    }

    public void altaPaciente(Long prontuarioId, String motivoAlta, Date dataAlta) {
        var prontuario = prontuarioRepository.findById(prontuarioId).orElseThrow(() -> new EntityNotFoundException("Prontuario não encontrado"));
        prontuario.setDataAlta(dataAlta);
        prontuario.setMotivoAlta(motivoAlta);
        var leito = leitosRepository.findById(prontuario.getLeito().getLeitoId()).orElseThrow(() -> new EntityNotFoundException("Leito não encontrado"));
        leito.setStatus("DISPONIVEL");
        leitosRepository.save(leito);
        prontuario.setLeito(null);
        prontuarioRepository.save(prontuario);
    }

    public void obitoPaciente(Long prontuarioId, String causaObito, Date dataObito) {
        var prontuario = prontuarioRepository.findById(prontuarioId).orElseThrow(() -> new EntityNotFoundException("Prontuario não encontrado"));
        prontuario.setDataObito(dataObito);
        prontuario.setCausaObito(causaObito);
        var leito = leitosRepository.findById(prontuario.getLeito().getLeitoId()).orElseThrow(() -> new EntityNotFoundException("Leito não encontrado"));
        leito.setStatus("DISPONIVEL");
        leitosRepository.save(leito);
        prontuario.setLeito(null);
        prontuarioRepository.save(prontuario);
    }

    public void transferirPaciente(Long prontuarioId, Long leitoId) {
        var prontuario = prontuarioRepository.findById(prontuarioId).orElseThrow(() -> new EntityNotFoundException("Prontuario não encontrado"));
        var leito = leitosRepository.findById(leitoId).orElseThrow(() -> new EntityNotFoundException("Leito antigo não encontrado"));
        leito.setStatus("OCUPADO");
        var leitoAntigo = leitosRepository.findById(prontuario.getLeito().getLeitoId()).orElseThrow(() -> new EntityNotFoundException("Leito novo não encontrado"));
        leitoAntigo.setStatus("DISPONIVEL");
        leitosRepository.save(leitoAntigo);
        leitosRepository.save(leito);
        prontuario.setLeito(leitosRepository.save(leito));
        prontuarioRepository.save(prontuario);
    }

    public Page<ProntuarioResponseDTO> listarProntuarios(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "prontuarioId"));
        Page<Prontuario> prontuarios = prontuarioRepository.findAll(pageable);
        
        return new PageImpl<>(
            prontuarios.getContent().stream()
                .map(prontuario -> {
                    PacienteResponseDTO pacienteDTO = mapToPacienteResponseDTO(prontuario.getPaciente());
                    ProfissionalResponseDTO profissionalDTO = mapToProfissionalResponseDTO(prontuario.getProfissional());
                    
                    return new ProntuarioResponseDTO(
                        prontuario.getNumeroProntuario(),
                        prontuario.getDataAdmissao(),
                        prontuario.getMotivoAdmissao(),
                        prontuario.getDiagnostico(),
                        prontuario.getHistoricoMedico(),
                        prontuario.getEvolucao(),
                        prontuario.getEncaminhamento(),
                        prontuario.getConduta(),
                        prontuario.getDataInternacao(),
                        prontuario.getDataAlta(),
                        prontuario.getMotivoInternacao(),
                        prontuario.getMotivoAlta(),
                        prontuario.getDataObito(),
                        prontuario.getCausaObito(),
                        prontuario.getLeito(),
                        pacienteDTO,
                        profissionalDTO
                    );
                })
                .collect(Collectors.toList()),
            pageable,
            prontuarios.getTotalElements()
        );
    }

    public ProntuarioResponseDTO buscarProntuarioPorId(Long prontuarioId) {
        var prontuario = prontuarioRepository.findById(prontuarioId).orElseThrow(() -> new EntityNotFoundException("Prontuario não encontrado"));
        PacienteResponseDTO pacienteDTO = mapToPacienteResponseDTO(prontuario.getPaciente());
        ProfissionalResponseDTO profissionalDTO = mapToProfissionalResponseDTO(prontuario.getProfissional());
        return new ProntuarioResponseDTO(
            prontuario.getNumeroProntuario(),
            prontuario.getDataAdmissao(),
            prontuario.getMotivoAdmissao(),
            prontuario.getDiagnostico(),
            prontuario.getHistoricoMedico(),
            prontuario.getEvolucao(),
            prontuario.getEncaminhamento(),
            prontuario.getConduta(),
            prontuario.getDataInternacao(),
            prontuario.getDataAlta(),
            prontuario.getMotivoInternacao(),
            prontuario.getMotivoAlta(),
            prontuario.getDataObito(),
            prontuario.getCausaObito(),
            prontuario.getLeito(),
            pacienteDTO,
            profissionalDTO
        );
    }

    private PacienteResponseDTO mapToPacienteResponseDTO(Paciente paciente) {
        return new PacienteResponseDTO(
            paciente.getNome(),
            paciente.getCpf(),
            paciente.getTelefone(),
            paciente.getEmail(),
            paciente.getDataNascimento(),
            paciente.getSexo(),
            paciente.getEscolaridade(),
            paciente.getOcupacao()
        );
    }

    private ProfissionalResponseDTO mapToProfissionalResponseDTO(Profissional profissional) {
        return new ProfissionalResponseDTO(
            profissional.getNome(),
            profissional.getCpf(),
            profissional.getTelefone(),
            profissional.getEmail(),
            profissional.getDataNascimento(),
            profissional.getEspecialidade(),
            profissional.getCrm(),
            profissional.getStatus()
        );
    }


}

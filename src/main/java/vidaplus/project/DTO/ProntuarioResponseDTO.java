package vidaplus.project.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import vidaplus.project.model.Leito;

import java.util.Date;

@Data
@AllArgsConstructor
public class ProntuarioResponseDTO {
    private String numeroProntuario;
    private Date dataAdmissao;
    private String motivoAdmissao;
    private String diagnostico;
    private String historicoMedico;
    private String evolucao;
    private String encaminhamento;
    private String conduta;
    private Date dataInternacao;
    private Date dataAlta;
    private String motivoInternacao;
    private String motivoAlta;
    private Date dataObito;
    private String causaObito;
    private Leito leito;
    private PacienteResponseDTO paciente;
    private ProfissionalResponseDTO profissional;
}

package vidaplus.project.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class ProntuarioDTO {
    private Date dataAdmissao;
    private String motivoAdmissao;
    private String diagnostico;
    private String historicoMedico;
    private String evolucao;
    private String encaminhamento;
    private String conduta;
    private Long pacienteId;
    private Long profissionalId;
    
}

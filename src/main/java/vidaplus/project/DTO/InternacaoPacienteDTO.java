package vidaplus.project.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class InternacaoPacienteDTO {
    private String motivoInternacao;
    private Date dataInternacao;
    private Long leitoId;
}


package vidaplus.project.DTO.ResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Date;

@Data
@AllArgsConstructor
public class ProfissionalResponseDTO {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private String especialidade;
    private String crm;
    private String status;
}

package vidaplus.project.DTO;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PacienteResponseDTO {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private String sexo;
    private String escolaridade;
    private String ocupacao;
    
}

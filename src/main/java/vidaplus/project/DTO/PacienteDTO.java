package vidaplus.project.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class PacienteDTO {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private String sexo;
    private String escolaridade;
    private String ocupacao;
    private EnderecoDTO endereco;
    
}

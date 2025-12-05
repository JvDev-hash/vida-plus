package vidaplus.project.DTO;

import lombok.Data;
import java.util.Date;

@Data
public class ProfissionalDTO {
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private String especialidade;
    private String crm;
    private String status;
    private EnderecoDTO endereco;
}

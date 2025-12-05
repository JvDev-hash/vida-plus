package vidaplus.project.DTO;

import lombok.Data;

@Data
public class EnderecoDTO {
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    
}

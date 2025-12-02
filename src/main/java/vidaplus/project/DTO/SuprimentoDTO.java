package vidaplus.project.DTO;

import java.math.BigInteger;

import lombok.Data;

@Data
public class SuprimentoDTO {
    private String nome;
    private String descricao;
    private BigInteger quantidade;
    private Integer quantidadeMinima;
    private String unidade;
    private String status;
}

package vidaplus.project.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.util.Date;
import java.math.BigInteger;

@Entity
@Data
public class Suprimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suprimento_id")
    private Long suprimentoId;
    private String nome;
    private String descricao;
    private BigInteger quantidade;
    private Integer quantidadeMinima;
    private String unidade;
    private String status;
    private Date dataCriacao;
    private Date dataAtualizacao;   
}
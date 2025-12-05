package vidaplus.project.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity
@Data
public class Leito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "leito_id")
    private Long leitoId;
    private String numero;
    private String descricao;
    private String status;
    private String tipo;
    private String alaMedica;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
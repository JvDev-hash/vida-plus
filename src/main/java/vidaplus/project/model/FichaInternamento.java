package vidaplus.project.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ManyToOne;

@Entity
@Data
public class FichaInternamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ficha_internamento_id")
    private Long fichaInternamentoId;
    private String dataInternacao;
    private String dataAlta;
    private String motivoInternacao;
    private String motivoAlta;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
    @OneToOne
    @JoinColumn(name = "leito_id")
    private Leito leito;
}


package vidaplus.project.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.util.Date;

@Entity
@Data
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prontuario_id")
    private Long prontuarioId;
    private String numeroProntuario;
    private Date dataAdmissao;
    private String motivoAdmissao;
    private String diagnostico;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String historicoMedico;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String evolucao;

    @Column(columnDefinition = "TEXT")
    private String encaminhamento;

    @Column(columnDefinition = "TEXT")
    private String conduta;
    

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
    @OneToOne
    @JoinColumn(name = "ficha_internamento_id", nullable = true)
    private FichaInternamento fichaInternamento;
    @OneToOne
    @JoinColumn(name = "ficha_obito_id", nullable = true)
    private FichaObito fichaObito;
}

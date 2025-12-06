
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
    @Column(name = "numero_prontuario", nullable = false)
    private String numeroProntuario;
    @Column(name = "data_admissao", nullable = false)
    private Date dataAdmissao;
    @Column(name = "motivo_admissao", nullable = false, columnDefinition = "TEXT")
    private String motivoAdmissao;
    @Column(name = "diagnostico", nullable = false, columnDefinition = "TEXT")
    private String diagnostico;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = true)
    private String historicoMedico;

    @Column(columnDefinition = "MEDIUMTEXT", nullable = true)
    private String evolucao;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String encaminhamento;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String conduta;

    @Column(name = "data_internacao", nullable = true)
    private Date dataInternacao;

    @Column(name = "data_alta", nullable = true)
    private Date dataAlta;

    @Column(name = "motivo_internacao", nullable = true, columnDefinition = "TEXT")
    private String motivoInternacao;

    @Column(name = "motivo_alta", nullable = true, columnDefinition = "TEXT")
    private String motivoAlta;

    @Column(name = "data_obito", nullable = true)
    private Date dataObito;

    @Column(name = "causa_obito", nullable = true, columnDefinition = "TEXT")
    private String causaObito;

    @OneToOne
    @JoinColumn(name = "leito_id", nullable = true)
    private Leito leito;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private Profissional profissional;
}

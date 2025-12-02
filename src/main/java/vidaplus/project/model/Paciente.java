package vidaplus.project.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.Date;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;

@Entity
@Data
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private Long pacienteId;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private String sexo;
    private String estadoCivil;
    private String profissao;
    private String naturalidade;
    private String alergias;
    private String medicacoes;
    private String historicoMedico;
    private String responsavelNome;
    private String responsavelTelefone;
    private String responsavelEmail;
    private String responsavelCpf;
    private String responsavelParentesco;
    @ManyToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

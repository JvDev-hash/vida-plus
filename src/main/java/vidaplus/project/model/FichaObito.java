package vidaplus.project.model;

import jakarta.persistence.Entity;
import lombok.Data;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

@Entity
@Data
public class FichaObito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ficha_obito_id")
    private Long fichaObitoId;
    private String dataObito;
    private String motivoObito;
}

package vidaplus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vidaplus.project.model.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
    
}

package vidaplus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vidaplus.project.model.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
}

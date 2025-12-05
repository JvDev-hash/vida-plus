package vidaplus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vidaplus.project.model.Profissional;

public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    
}

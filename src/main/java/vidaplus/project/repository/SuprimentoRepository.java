package vidaplus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vidaplus.project.model.Suprimento;

public interface SuprimentoRepository extends JpaRepository<Suprimento, Long> {
    
}

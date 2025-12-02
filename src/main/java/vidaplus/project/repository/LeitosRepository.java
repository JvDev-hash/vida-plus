package vidaplus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vidaplus.project.model.Leito;

public interface LeitosRepository extends JpaRepository<Leito, Long> {
    
}

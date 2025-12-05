package vidaplus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vidaplus.project.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    
}

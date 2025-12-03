package vidaplus.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vidaplus.project.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}

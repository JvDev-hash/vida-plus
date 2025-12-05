package vidaplus.project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import vidaplus.project.model.Usuario;

public interface LoginRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(@Param("email") String email);
}

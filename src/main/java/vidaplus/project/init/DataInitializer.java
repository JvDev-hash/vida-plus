package vidaplus.project.init;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import vidaplus.project.model.Usuario;
import vidaplus.project.repository.LoginRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    private final LoginRepository loginRepository;

    public DataInitializer(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        var usuario = loginRepository.findByEmail("admin@vidaplus.com");

        if (usuario.isEmpty()) {
            Usuario adminUsuario = new Usuario();
            adminUsuario.setNome("Administrador");
            adminUsuario.setEmail("admin@vidaplus.com");
            
            adminUsuario.setSenha(BCrypt.hashpw("admin123", BCrypt.gensalt()));
            adminUsuario.setPermissao("ADMIN");
            adminUsuario.setStatus("ATIVO");
            adminUsuario.setDataCriacao(new Date());
            adminUsuario.setDataAtualizacao(new Date());

            loginRepository.save(adminUsuario);
            System.out.println("Usuário inicial criado: admin@vidaplus.com / admin123");
        } else {
            System.out.println("Usuário inicial já existe no banco de dados.");
        }
    }
}


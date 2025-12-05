package vidaplus.project.service;

import vidaplus.project.model.Usuario;
import vidaplus.project.repository.LoginRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository loginRepository;

    @Autowired
    public LoginService(LoginRepository loginRepository){
        this.loginRepository = loginRepository;
    }

    public Usuario getByEmailAndPassword(String email, String password){
        var usuario = loginRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o email: " + email));

        if(BCrypt.checkpw(password, usuario.getSenha())){
            return usuario;
        } else {
            return null;
        }
    }
}

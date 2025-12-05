package vidaplus.project.controller;

import java.nio.charset.StandardCharsets;

import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.Cookie;

import vidaplus.project.service.LoginService;
import vidaplus.project.service.JwtTokenService;
import vidaplus.project.DTO.LoginDTO;
import vidaplus.project.DTO.LoginResponseDTO;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final LoginService loginService;
    private final JwtTokenService jwtTokenService;

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    public LoginController(LoginService loginService, JwtTokenService jwtTokenService){
        this.loginService = loginService;
        this.jwtTokenService = jwtTokenService;
    }
    
    @PostMapping("/autenticar")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response){
        try{
            var usuario = loginService.getByEmailAndPassword(loginDTO.getEmail(), loginDTO.getSenha());

            if (usuario == null) {
                return new ResponseEntity<>("Usuário ou senha errados!", HttpStatus.NOT_FOUND);
            }

            var tokenJWT = jwtTokenService.generateToken(usuario);

            Cookie cookie = new Cookie("token", tokenJWT);
            cookie.setHttpOnly(true);
            cookie.setSecure(false);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60 * 24);

            response.addCookie(cookie);

            return new ResponseEntity<>(new LoginResponseDTO(usuario.getNome(), usuario.getPermissao()), HttpStatus.OK);

        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>("Internal Server Error: " + e.getClass().getName(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}

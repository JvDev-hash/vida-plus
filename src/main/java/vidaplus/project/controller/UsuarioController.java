package vidaplus.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.nio.charset.StandardCharsets;
import vidaplus.project.DTO.UsuarioDTO;
import vidaplus.project.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    // Cadastro de usuário
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {

        try{
            usuarioService.cadastrarUsuario(usuarioDTO);
            return new ResponseEntity<>("Usuario cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Listagem de usuários
    @GetMapping("/listar")
    public ResponseEntity<?> listarUsuarios(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        try{
            var usuarios = usuarioService.listarUsuarios(pageNo, pageSize);
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Edição de usuário
    @PutMapping("/{id}")
    public ResponseEntity<?> editarUsuario(@PathVariable("id") Long id, @RequestBody UsuarioDTO usuarioDTO) {

        try{
            usuarioService.editarUsuario(id, usuarioDTO);
            return new ResponseEntity<>("Usuario editado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }

    // Inativação de usuário
    @PutMapping("/inativar/{id}")
    public ResponseEntity<?> inativarUsuario(@PathVariable("id") Long id) {

        try{
            usuarioService.inativarUsuario(id);
            return new ResponseEntity<>("Usuario inativado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    }
}


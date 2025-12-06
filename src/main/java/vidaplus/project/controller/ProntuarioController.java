package vidaplus.project.controller;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vidaplus.project.DTO.ProntuarioDTO;
import vidaplus.project.service.ProntuarioService;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }
    
    Logger logger = LoggerFactory.getLogger(ProntuarioController.class);

    @PostMapping("/admissao")
    public ResponseEntity<?> admissaoPaciente(@RequestBody ProntuarioDTO prontuarioDTO) {
        try{
            prontuarioService.admissaoPaciente(prontuarioDTO);
            return new ResponseEntity<>("Paciente admisso com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }   
    }

    @PutMapping("/internacao/{prontuarioId}")
    public ResponseEntity<?> internacaoPaciente(@PathVariable Long prontuarioId, @RequestBody String motivoInternacao, @RequestBody Long leitoId) {
        try{
            prontuarioService.internacaoPaciente(prontuarioId, motivoInternacao, new Date(), leitoId);
            return new ResponseEntity<>("Paciente internado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }   
    }  

    @PutMapping("/editar/{prontuarioId}")
    public ResponseEntity<?> editarProntuario(@PathVariable Long prontuarioId, @RequestBody ProntuarioDTO prontuarioDTO) {
        try{
            prontuarioService.editarProntuario(prontuarioId, prontuarioDTO);
            return new ResponseEntity<>("Prontuario editado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }   
    }

    @PutMapping("/alta/{prontuarioId}")
    public ResponseEntity<?> altaPaciente(@PathVariable Long prontuarioId, @RequestBody Date dataAlta, String motivoAlta) {
        try{
            prontuarioService.altaPaciente(prontuarioId, motivoAlta, dataAlta);
            return new ResponseEntity<>("Paciente dado de alta com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }   
    }

    @PutMapping("/obito/{prontuarioId}")
    public ResponseEntity<?> obitoPaciente(@PathVariable Long prontuarioId, @RequestBody Date dataObito, String causaObito) {
        try{
            prontuarioService.altaPaciente(prontuarioId, causaObito, dataObito);
            return new ResponseEntity<>("Registro de óbito realizado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }   
    }

    @PutMapping("/transferir/{prontuarioId}")
    public ResponseEntity<?> transferirPaciente(@PathVariable Long prontuarioId, @RequestBody Long leitoId) {
        try{
            prontuarioService.transferirPaciente(prontuarioId, leitoId);
            return new ResponseEntity<>("Paciente transferido com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }   
    }

    @GetMapping("/listar")
    public ResponseEntity<?> listarProntuarios(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        try{
            var prontuarios = prontuarioService.listarProntuarios(pageNo, pageSize);
            return new ResponseEntity<>(prontuarios, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

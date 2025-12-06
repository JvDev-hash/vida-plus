package vidaplus.project.controller;

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

import vidaplus.project.DTO.PacienteDTO;
import vidaplus.project.DTO.ProfissionalDTO;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vidaplus.project.service.PessoasService;

@RestController
@RequestMapping("/pessoas")
public class PessoasController {

    private final PessoasService pessoasService;

    public PessoasController(PessoasService pessoasService) {
        this.pessoasService = pessoasService;
    }

    Logger logger = LoggerFactory.getLogger(PessoasController.class);

    @PostMapping("/profissional")
    public ResponseEntity<?> cadastrarProfissional(@RequestBody ProfissionalDTO profissionalDTO) {
        try{
            pessoasService.cadastrarProfissional(profissionalDTO);
            return new ResponseEntity<>("Profissional cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profissional")
    public ResponseEntity<?> listarProfissionais(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        try{
            var profissionais = pessoasService.listarProfissionais(pageNo, pageSize);
            return new ResponseEntity<>(profissionais, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/profissional/{id}")
    public ResponseEntity<?> editarProfissional(@PathVariable Long id, @RequestBody ProfissionalDTO profissionalDTO) {
        try{
            pessoasService.editarProfissional(id, profissionalDTO);
            return new ResponseEntity<>("Profissional atualizado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping("/profissional/status/{id}")
    public ResponseEntity<?> mudarStatusProfissional(@PathVariable Long id, @RequestBody String status) {
        try{
            pessoasService.mudarStatusProfissional(id, status);
            return new ResponseEntity<>("Profissional status mudado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/paciente")
    public ResponseEntity<?> cadastrarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        try {
            pessoasService.cadastrarPaciente(pacienteDTO);
            return new ResponseEntity<>("Paciente cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/paciente/{id}")
    public ResponseEntity<?> editarPaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        try {
            pessoasService.editarPaciente(id, pacienteDTO);
            return new ResponseEntity<>("Paciente atualizado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/paciente")
    public ResponseEntity<?> listarPacientes(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        try {
            var pacientes = pessoasService.listarPacientes(pageNo, pageSize);
            return new ResponseEntity<>(pacientes, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("/paciente/{id}")
    public ResponseEntity<?> buscarPacientePorId(@PathVariable Long id) {
        try {
            var paciente = pessoasService.buscarPacientePorId(id);
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if (e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
}

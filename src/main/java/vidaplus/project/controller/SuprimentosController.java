package vidaplus.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vidaplus.project.DTO.SuprimentoDTO;
import vidaplus.project.service.SuprimentosService;

@RestController
@RequestMapping("/suprimentos")
public class SuprimentosController {

    private final SuprimentosService suprimentoService;

    public SuprimentosController(SuprimentosService suprimentoService) {
        this.suprimentoService = suprimentoService;
    }

    Logger logger = LoggerFactory.getLogger(SuprimentosController.class);
    

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarSuprimento(@RequestBody SuprimentoDTO suprimentoDTO) {
        try{
            suprimentoService.cadastrarSuprimento(suprimentoDTO);
            return new ResponseEntity<>("Suprimento cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/atualizar/{suprimentoId}")
    public ResponseEntity<?> atualizarSuprimento(@PathVariable Long suprimentoId, @RequestBody SuprimentoDTO suprimentoDTO) {
        try{
            suprimentoService.atualizarSuprimento(suprimentoId, suprimentoDTO);
            return new ResponseEntity<>("Suprimento atualizado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }
    
    @GetMapping("/listar")
    public ResponseEntity<?> listarSuprimentos(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        try{
            var listaSuprimentos = suprimentoService.listarSuprimentos(pageNo, pageSize);
            return new ResponseEntity<>(listaSuprimentos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

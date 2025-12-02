package vidaplus.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vidaplus.project.DTO.LeitoDTO;
import vidaplus.project.service.LeitosService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/leitos")
public class LeitosController {

    private final LeitosService leitoService;

    public LeitosController(LeitosService leitoService) {
        this.leitoService = leitoService;
    }
    
    Logger logger = LoggerFactory.getLogger(LeitosController.class);

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrarLeito(@RequestBody LeitoDTO leitoDTO) {

        try{
            leitoService.cadastrarLeito(leitoDTO);
            return new ResponseEntity<>("Leito cadastrado com sucesso!", HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping("/editar/{leitoId}")
    public ResponseEntity<?> editarLeito(@PathVariable Long leitoId, @RequestBody LeitoDTO leitoDTO) {

        try{
            leitoService.editarLeito(leitoId, leitoDTO);
            return new ResponseEntity<>("Leito editado com sucesso!", HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            if(e.getClass().getName().equals("jakarta.persistence.EntityNotFoundException")) {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }


    @PutMapping("/status/{leitoId}")
    public ResponseEntity<?> mudarStatusLeito(@PathVariable Long leitoId, @RequestBody String status) {

            try{
                leitoService.mudarStatusLeito(leitoId, status);
                return new ResponseEntity<>("Leito status mudado com sucesso!", HttpStatus.OK);
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
    public ResponseEntity<?> listarLeitos(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {

        try{
            var listaLeitos = leitoService.listarLeitos(pageNo, pageSize);
            return new ResponseEntity<>(listaLeitos, HttpStatus.OK);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new ResponseEntity<>(e.getMessage().getBytes(StandardCharsets.UTF_8), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

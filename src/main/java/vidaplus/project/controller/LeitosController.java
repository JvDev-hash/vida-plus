package vidaplus.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/leitos")
public class LeitosController {
    // Endpoint para cadastro de leitos
    @PostMapping
    public String cadastrarLeito(@RequestBody String leito) {
        // Lógica para cadastrar leito
        return "Leito cadastrado com sucesso!";
    }

    // Endpoint para edição de leitos
    @PutMapping("/{leitoId}")
    public String editarLeito(@PathVariable Long leitoId, @RequestBody String leito) {
        // Lógica para editar leito com base no id
        return "Leito editado com sucesso!";
    }

    // Endpoint para inativação de leitos
    @PutMapping("/status/{leitoId}")
    public String inativarLeito(@PathVariable Long leitoId) {
        // Lógica para inativar leito com base no id
        return "Leito inativado com sucesso!";
    }
}

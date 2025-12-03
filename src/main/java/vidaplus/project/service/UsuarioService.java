package vidaplus.project.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import vidaplus.project.DTO.UsuarioDTO;
import vidaplus.project.model.Usuario;
import vidaplus.project.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void cadastrarUsuario(UsuarioDTO usuarioDTO) {
        var usuario = new Usuario();
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPermissao(usuarioDTO.getPermissao());
        usuario.setStatus(usuarioDTO.getStatus());
        usuario.setDataCriacao(new Date());
        usuario.setDataAtualizacao(new Date());
        usuarioRepository.save(usuario);
    }

    public void editarUsuario(Long id, UsuarioDTO usuarioDTO) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPermissao(usuarioDTO.getPermissao());
        usuario.setStatus(usuarioDTO.getStatus());
        usuario.setDataAtualizacao(new Date());
        usuarioRepository.save(usuario);
    }

    public void inativarUsuario(Long id) {
        var usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));
        usuario.setStatus("Inativo");
        usuario.setDataAtualizacao(new Date());
        usuarioRepository.save(usuario);
    }

    public Page<Usuario> listarUsuarios(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(Sort.Direction.DESC, "usuarioId"));
        Page<Usuario> usuarios = usuarioRepository.findAll(pageable);
        return new PageImpl<>(usuarios.getContent(), pageable, usuarios.getTotalElements());
    }
    
}

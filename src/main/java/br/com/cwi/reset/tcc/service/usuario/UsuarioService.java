package br.com.cwi.reset.tcc.service.usuario;

import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.exception.usuario.UsuarioComMesmoCpfException;
import br.com.cwi.reset.tcc.exception.usuario.UsuarioComMesmoEmailException;
import br.com.cwi.reset.tcc.exception.usuario.UsuarioIdEnderecoBadRequestException;
import br.com.cwi.reset.tcc.exception.usuario.UsuarioIdNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findUsuarioById(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(!usuario.isPresent()){
            throw new UsuarioIdNaoEncontradoException();
        }
        return usuario.get();
    }

    public Usuario findUsuarioComEnderecoById(Long id){
        Usuario usuario = findUsuarioById(id);
        validaUsuarioComEndereco(usuario);
        return usuario;
    }

    public Page<Usuario> findUsuarioByPageable(Pageable pageable){
        return usuarioRepository.findAll(pageable);
    }

    public Usuario saveUsuario(Usuario usuario){
        validaUsuarioByEmail(usuario);
        validaUsuarioByCpf(usuario);
        return usuarioRepository.save(usuario);
    }

    public void validaUsuarioByEmail(Usuario usuario){
        if(usuarioRepository.existsByEmail(usuario.getEmail())){
            throw new UsuarioComMesmoEmailException();
        }
    }

    public void validaUsuarioByCpf(Usuario usuario){
        if(usuarioRepository.existsByCpf(usuario.getCpf())){
            throw new UsuarioComMesmoCpfException();
        }
    }

    public void validaUsuarioComEndereco(Usuario usuario){
        if(usuario.getEnderecos().isEmpty()){
            throw new UsuarioIdEnderecoBadRequestException();
        }
    }
}

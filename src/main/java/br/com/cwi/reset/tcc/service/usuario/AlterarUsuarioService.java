
package br.com.cwi.reset.tcc.service.usuario;

import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.repository.EnderecoRepository;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioService usuarioService;

    public Usuario alterarUsuario(Usuario novoUsuario,Long id) {

        validaCamposObrigatorios(novoUsuario);
        usuarioService.validaUsuarioByEmail(novoUsuario);
        Usuario usuarioCadastrado = usuarioService.findUsuarioById(id);

        usuarioCadastrado.setNome(novoUsuario.getNome());
        usuarioCadastrado.setEmail(novoUsuario.getEmail());
        usuarioCadastrado.setSenha(novoUsuario.getSenha());
        if(novoUsuario.getEnderecos() != null){
            usuarioCadastrado.getEnderecos().addAll(novoUsuario.getEnderecos());
        }

        return usuarioRepository.save(usuarioCadastrado);
    }

    private boolean isCamposPreenchidos(Usuario usuario){
        return usuario.getEmail() != null && usuario.getNome() != null
                && usuario.getSenha() != null;
    }

    private void validaCamposObrigatorios(Usuario usuario){
        if(!isCamposPreenchidos(usuario)){
            throw new RuntimeException("Favor preencha os campos: Email, Nome e Senha!");
        }
    }
}

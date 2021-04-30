package br.com.cwi.reset.tcc.service.usuario;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.exception.usuario.UsuarioNaoAssociadoAoEnderecoException;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import br.com.cwi.reset.tcc.service.enderecos.EnderecosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarEnderecoUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private UsuarioService usuarioService;

    public void deletarEndereco(Long id, Long idEndereco) {

        Usuario usuario = usuarioService.findUsuarioComEnderecoById(id);
        Endereco endereco = enderecosService.findEnderecoById(idEndereco);

        if(!usuario.getEnderecos().contains(endereco)){
            throw new UsuarioNaoAssociadoAoEnderecoException();
        }
        usuario.getEnderecos().remove(endereco);
        enderecosService.deleteEndereco(endereco);
    }
}

package br.com.cwi.reset.tcc.service.usuario;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.repository.EnderecoRepository;
import br.com.cwi.reset.tcc.service.enderecos.EnderecosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastrarEnderecoUsuarioService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private UsuarioService usuarioService;


    public Endereco salvarEndereco(Endereco endereco, Long id) {

        Usuario novoUsuario = usuarioService.findUsuarioById(id);
        List<Endereco> novoEndereco = novoUsuario.getEnderecos();
        novoEndereco.add(endereco);
        novoUsuario.setEnderecos(novoEndereco);
        return enderecoRepository.save(endereco);
    }
}

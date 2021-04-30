package br.com.cwi.reset.tcc.service.estabelecimento;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.repository.EnderecoRepository;
import br.com.cwi.reset.tcc.service.enderecos.EnderecosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CadastrarEnderecoEstabelecimentoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    public Endereco salvarEnderecoEstabelecimento(Endereco endereco, Long id) {

        Estabelecimento novoEstabelecimento = estabelecimentoService.findEstabelecimentoById(id);
        List<Endereco> novoEndereco = novoEstabelecimento.getEnderecos();
        novoEndereco.add(endereco);
        novoEstabelecimento.setEnderecos(novoEndereco);

        return enderecoRepository.save(endereco);
    }
}

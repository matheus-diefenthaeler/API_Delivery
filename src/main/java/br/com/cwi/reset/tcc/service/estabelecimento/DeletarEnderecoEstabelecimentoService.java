package br.com.cwi.reset.tcc.service.estabelecimento;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.exception.estabelecimento.EstabelecimentoNaoAssociadoAoEnderecoException;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import br.com.cwi.reset.tcc.service.enderecos.EnderecosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarEnderecoEstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    public void deletarEstabelecimentoEndereco(Long id, Long idEndereco) {

        Estabelecimento estabelecimento = estabelecimentoService.findEstabelecimentoById(id);
        Endereco endereco = enderecosService.findEnderecoById(idEndereco);
        enderecosService.validaEnderecoEstabelecimento(idEndereco);

        if(!estabelecimento.getEnderecos().contains(endereco)){
            throw new EstabelecimentoNaoAssociadoAoEnderecoException();
        }
        estabelecimento.getEnderecos().remove(endereco);
        enderecosService.deleteEndereco(endereco);
    }
}

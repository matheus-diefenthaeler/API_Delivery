package br.com.cwi.reset.tcc.service.enderecos;


import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.exception.endereco.EnderecoIdNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.EnderecoRepository;
import br.com.cwi.reset.tcc.request.pedido.PedidoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecosService {

    @Autowired
    EnderecoRepository enderecoRepository;

    public Endereco saveEndereco(Endereco endereco){
        return enderecoRepository.save(endereco);
    }

    public Endereco findEnderecoById(Long id){
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        if(!endereco.isPresent()){
            throw new EnderecoIdNaoEncontradoException();
        }
        return endereco.get();
    }

    public void deleteEndereco(Endereco endereco){
        enderecoRepository.delete(endereco);
    }
    public void deleteEnderecoById(Long id){
        enderecoRepository.deleteById(id);
    }
    public boolean existsById(Long id){
        return enderecoRepository.existsById(id);
    }

    public void validaEnderecoEstabelecimento(Long id) {
        if (!enderecoRepository.existsById(id)) {
            throw new EnderecoIdNaoEncontradoException();
        }
    }

    public Endereco verificaEnderecoUsuario(PedidoRequest fazerPedidoRequest) {
        return findEnderecoById(fazerPedidoRequest.getIdEnderecoEntrega());
    }

    public boolean isCamposEnderecoPreenchidos(Endereco endereco){
        return endereco.getCep() != null && endereco.getLogradouro() != null
                && endereco.getNumero() != null && endereco.getBairro() != null
                && endereco.getCidade() != null && endereco.getEstado() != null;
    }

    public void verificaCamposEnderecoPreenchidos(Endereco endereco){
        if(!isCamposEnderecoPreenchidos(endereco)){
            throw new RuntimeException("Preencha os Campos obrigatorios para o endereco");
        }
    }
}

package br.com.cwi.reset.tcc.service.produto;

import br.com.cwi.reset.tcc.exception.produto.ProdutoIdNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void validaProdutoId(Long id) {
        if(!produtoRepository.existsById(id)){
            throw new ProdutoIdNaoEncontradoException();
        }
    }
}

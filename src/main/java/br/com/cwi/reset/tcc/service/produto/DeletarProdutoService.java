package br.com.cwi.reset.tcc.service.produto;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired ProdutoService produtoService;


    public void deletarProduto(Long id) {

        produtoService.validaProdutoId(id);
        Produto produto = produtoRepository.getOne(id);
        produtoRepository.delete(produto);
    }
}

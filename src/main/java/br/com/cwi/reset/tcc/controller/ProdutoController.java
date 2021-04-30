package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import br.com.cwi.reset.tcc.request.produto.ProdutoRequest;
import br.com.cwi.reset.tcc.response.produto.ProdutoResponse;
import br.com.cwi.reset.tcc.service.produto.CadastrarProdutoService;
import br.com.cwi.reset.tcc.service.produto.DeletarProdutoService;
import br.com.cwi.reset.tcc.service.produto.ListarProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CadastrarProdutoService cadastrarProdutoService;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private ListarProdutoService listarProdutoService;

    @Autowired
    private DeletarProdutoService deletarProdutoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse cadastrarProduto(@RequestBody ProdutoRequest produtoRequest) {
        return cadastrarProdutoService.salvarProduto(produtoRequest);
    }

    @GetMapping
    public Page<Produto> getProdutos(Pageable pageable){
        return listarProdutoService.getProdutos(pageable);
    }

    @DeleteMapping("/{id}")
    public void deletarProduto(@PathVariable("id") final @Valid Long id){
        deletarProdutoService.deletarProduto(id);
    }

}

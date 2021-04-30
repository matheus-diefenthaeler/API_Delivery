package br.com.cwi.reset.tcc.service.produto;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.exception.produto.ProdutoPrecoInvalidoException;
import br.com.cwi.reset.tcc.mapper.produto.ProdutoMapper;
import br.com.cwi.reset.tcc.mapper.produto.ProdutoResponseMapper;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import br.com.cwi.reset.tcc.request.produto.ProdutoRequest;
import br.com.cwi.reset.tcc.response.produto.ProdutoResponse;
import br.com.cwi.reset.tcc.service.estabelecimento.EstabelecimentoService;
import br.com.cwi.reset.tcc.service.estabelecimento.ListarEstabelecimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private ListarEstabelecimentoService listarEstabelecimentoService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    public ProdutoResponse salvarProduto(ProdutoRequest produtoRequest){

        checkProdutoCampos(produtoRequest);
        checkProdutoValor(produtoRequest);
        Produto produtoSalvar = ProdutoMapper.toProduto(produtoRequest);
        produtoSalvar.setEstabelecimento(verificaEstabelecimento(produtoRequest));
        return ProdutoResponseMapper.toProdutoResponse(produtoRepository.save(produtoSalvar));
    }

    private Estabelecimento verificaEstabelecimento(ProdutoRequest produtoRequest){
        return estabelecimentoService.findEstabelecimentoById(produtoRequest.getIdEstabelecimento());
    }

    private Boolean verificaValor(ProdutoRequest produtoRequest){
        return (produtoRequest.getValor().doubleValue() <= 0);
    }

    private Boolean isCamposPreenchidos(ProdutoRequest produtoRequest){
        return produtoRequest.getDescricao() != null && produtoRequest.getTitulo() != null
                && produtoRequest.getValor() != null && produtoRequest.getIdEstabelecimento() != null;
    }

    private void checkProdutoValor(ProdutoRequest produtoRequest){
        if(verificaValor(produtoRequest)){
            throw new ProdutoPrecoInvalidoException();
        }
    }

    private void checkProdutoCampos(ProdutoRequest produtoRequest){
        if(!isCamposPreenchidos(produtoRequest)){
            throw new RuntimeException("Preencha os campos obrigatórios");
        }
    }
}

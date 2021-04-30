package br.com.cwi.reset.tcc.mapper.produto;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.request.produto.ProdutoRequest;

public class ProdutoMapper {

    private static final Integer minTempo = 30;

    public static Produto toProduto(ProdutoRequest produtoRequest) {

        Produto produto = new Produto();

        produto.setDescricao(produtoRequest.getDescricao());
        produto.setTitulo(produtoRequest.getTitulo());
        produto.setValor(produtoRequest.getValor());
        produto.setCategoria(produtoRequest.getCategoria());
        produto.setTempoPreparo(produtoRequest.getTempoPreparo());
        produto.setUrlFoto(produtoRequest.getUrlFoto());

        if(produto.getTempoPreparo() == null){
            produto.setTempoPreparo(minTempo);
        }
        return produto;
    }
}

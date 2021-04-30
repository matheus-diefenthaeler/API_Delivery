package br.com.cwi.reset.tcc.mapper.produto;

import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.response.produto.ProdutoResponse;

public class ProdutoResponseMapper {


    public static ProdutoResponse toProdutoResponse(Produto produto){

        return new ProdutoResponse(produto.getId(),produto.getTitulo(), produto.getDescricao(),
                produto.getValor(), produto.getEstabelecimento().getCnpj(), produto.getCategoria(),
                produto.getTempoPreparo(), produto.getUrlFoto());
    }
}

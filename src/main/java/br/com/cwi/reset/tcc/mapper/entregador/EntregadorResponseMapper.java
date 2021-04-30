package br.com.cwi.reset.tcc.mapper.entregador;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.request.produto.ProdutoRequest;
import br.com.cwi.reset.tcc.response.entregador.EntregadorResponse;
import br.com.cwi.reset.tcc.response.produto.ProdutoResponse;

public class EntregadorResponseMapper {

    public static EntregadorResponse toEntregadorResponse(Entregador entregador){

        return new EntregadorResponse(entregador.getId(),entregador.getCpf(), entregador.getNome(),
                entregador.getTelefone(), entregador.getPlacaVeiculo());
    }
}

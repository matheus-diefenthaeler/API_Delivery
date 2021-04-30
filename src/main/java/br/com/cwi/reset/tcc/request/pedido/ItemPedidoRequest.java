package br.com.cwi.reset.tcc.request.pedido;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoRequest {

    private Long idProduto;
    private Integer quantidade;

}

package br.com.cwi.reset.tcc.request.pedido;

import br.com.cwi.reset.tcc.dominio.FormaPagamento;
import lombok.Getter;

import java.util.List;

@Getter
public class PedidoRequest {

    private Long idEstabelecimento;
    private Long idUsuarioSolicitante;
    private Long idEnderecoEntrega;
    private FormaPagamento formaPagamento;
    private List<ItemPedidoRequest> itens;

}

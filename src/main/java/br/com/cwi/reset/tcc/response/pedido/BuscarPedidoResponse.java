package br.com.cwi.reset.tcc.response.pedido;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.dominio.ItemPedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.request.pedido.ItemPedidoRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class BuscarPedidoResponse {

    private String nomeSolicitante;
    private Endereco enderecoEntrega;
    private String nomeEstabelecimento;
    private List<ItemPedidoResponse> itensPedido;
    private BigDecimal valorTotal;
    private Entregador entregador;
    private LocalDateTime horarioPrevistoParaEntrega;
    private StatusPedido situacao;


}

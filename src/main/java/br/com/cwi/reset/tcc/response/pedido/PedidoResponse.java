package br.com.cwi.reset.tcc.response.pedido;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PedidoResponse {

    private final Long idPedido;
    private BigDecimal valorTotal;
    private StatusPedido statusPedido;
    private Endereco enderecoEntrega;
    private Integer tempoEstimadoEmMinutos;

    public PedidoResponse(Long idPedido, BigDecimal valorTotal, StatusPedido statusPedido, Endereco enderecoEntrega) {

        this.idPedido = idPedido;
        this.valorTotal = valorTotal;
        this.statusPedido = statusPedido;
        this.enderecoEntrega = enderecoEntrega;
    }
}

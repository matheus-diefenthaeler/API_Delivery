package br.com.cwi.reset.tcc.mapper.pedido;

import br.com.cwi.reset.tcc.dominio.ItemPedido;
import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.response.pedido.BuscarPedidoResponse;
import br.com.cwi.reset.tcc.response.pedido.ItemPedidoResponse;
import br.com.cwi.reset.tcc.service.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BuscarPedidoResponseMapper {

    @Autowired
    private PedidoService pedidoService;


    public static BuscarPedidoResponse toBuscarPedidoResponse(Pedido pedido, LocalDateTime tempoPrevistoDeEntrega) {

        BuscarPedidoResponse buscarPedidoResponse = new BuscarPedidoResponse();

        buscarPedidoResponse.setNomeSolicitante(pedido.getSolicitante().getNome());
        buscarPedidoResponse.setEnderecoEntrega(pedido.getEnderecoEntrega());
        buscarPedidoResponse.setNomeEstabelecimento(pedido.getEstabelecimento().getNomeFantasia());

        List<ItemPedidoResponse> itemPedidoResponseList = new ArrayList<>();
        for (ItemPedido itemPedido : pedido.getItensPedido()){

            itemPedidoResponseList.add(new ItemPedidoResponse(itemPedido.getProduto().getTitulo(),itemPedido.getQuantidade()));
        }

        buscarPedidoResponse.setItensPedido(itemPedidoResponseList);

        buscarPedidoResponse.setValorTotal(pedido.getValorTotal());
        if(pedido.getStatus() == StatusPedido.SAIU_PARA_ENTREGA || pedido.getStatus() == StatusPedido.ENTREGUE){
            buscarPedidoResponse.setEntregador(pedido.getEntregador());
        }
        if(pedido.getStatus() != StatusPedido.ENTREGUE){
            buscarPedidoResponse.setHorarioPrevistoParaEntrega(tempoPrevistoDeEntrega);
        }
        buscarPedidoResponse.setSituacao(pedido.getStatus());

        return  buscarPedidoResponse;
    }
}

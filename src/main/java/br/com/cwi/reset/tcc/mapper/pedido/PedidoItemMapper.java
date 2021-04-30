package br.com.cwi.reset.tcc.mapper.pedido;

import br.com.cwi.reset.tcc.dominio.ItemPedido;
import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.request.pedido.ItemPedidoRequest;

import java.util.ArrayList;
import java.util.List;

public class PedidoItemMapper {

    public static ItemPedido toItemPedido(Produto produto, ItemPedidoRequest itemPedidoRequest){

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setProduto(produto);
        itemPedido.setQuantidade(itemPedidoRequest.getQuantidade());

        return itemPedido;
    }

    public static List<ItemPedido> toItemPedidoList(Produto produto, List<ItemPedidoRequest> pedidoItemRequest){

        List<ItemPedido> itensPedidos = new ArrayList<>();

        for(ItemPedidoRequest itemRequest : pedidoItemRequest){
            itensPedidos.add(toItemPedido(produto, itemRequest));
        }
        return  itensPedidos;
    }
}

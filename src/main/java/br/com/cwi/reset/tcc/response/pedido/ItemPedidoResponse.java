package br.com.cwi.reset.tcc.response.pedido;

import lombok.Getter;

@Getter
public class ItemPedidoResponse {


    private String titulo;
    private Integer quantidade;

    public ItemPedidoResponse(String titulo, Integer quantidade) {
        this.titulo = titulo;
        this.quantidade = quantidade;
    }
}

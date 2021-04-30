package br.com.cwi.reset.tcc.exception.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoSaiuParaEntregaException extends RuntimeException{

    public PedidoSaiuParaEntregaException() {
        super("O pedido não pode ser finalizado pois, ainda não saiu para entrega!");
    }
}

package br.com.cwi.reset.tcc.exception.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoEmPreparoException extends RuntimeException{

    public PedidoEmPreparoException() {
        super("O pedido não pode ser entregue pois, não está mais em preparo!");
    }
}

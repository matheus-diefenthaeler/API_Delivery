package br.com.cwi.reset.tcc.exception.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PedidoCancelarException extends RuntimeException{

    public PedidoCancelarException() {
        super("O pedido não pode ser cancelado pois, não está mais em preparo!");
    }
}

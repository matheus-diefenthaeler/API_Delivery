package br.com.cwi.reset.tcc.exception.pedido;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TempoExcedidoException extends RuntimeException{

    public TempoExcedidoException() {
        super("Não é permitido cancelar um pedido após 10 minutos!");
    }
}

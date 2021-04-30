package br.com.cwi.reset.tcc.exception.entregador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntregadorNaoEncontradoException extends RuntimeException{

    public EntregadorNaoEncontradoException() {
        super("Entregador não encontrado!");
    }
}
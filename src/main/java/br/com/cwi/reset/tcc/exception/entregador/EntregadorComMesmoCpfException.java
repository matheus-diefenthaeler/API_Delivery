package br.com.cwi.reset.tcc.exception.entregador;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntregadorComMesmoCpfException extends RuntimeException{

    public EntregadorComMesmoCpfException() {
        super("Nao e possivel cadastrar um Entregador com o mesmo CPF!");
    }
}
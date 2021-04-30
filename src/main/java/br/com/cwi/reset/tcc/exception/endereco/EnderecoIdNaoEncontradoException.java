package br.com.cwi.reset.tcc.exception.endereco;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EnderecoIdNaoEncontradoException extends RuntimeException{

    public EnderecoIdNaoEncontradoException() {
        super("Endereço nao cadastrado!");
    }
}
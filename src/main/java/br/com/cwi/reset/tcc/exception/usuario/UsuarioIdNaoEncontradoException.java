package br.com.cwi.reset.tcc.exception.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioIdNaoEncontradoException extends RuntimeException{

    public UsuarioIdNaoEncontradoException() {
        super("ID nao encontrado, por favor, informe um ID cadastrado.");
    }
}
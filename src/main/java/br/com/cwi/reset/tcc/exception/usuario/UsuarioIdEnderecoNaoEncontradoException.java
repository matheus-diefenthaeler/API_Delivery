package br.com.cwi.reset.tcc.exception.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioIdEnderecoNaoEncontradoException extends RuntimeException{

    public UsuarioIdEnderecoNaoEncontradoException() {
        super("ID endereco nao encontrado, por favor, informe um ID de endereco cadastrado.");
    }
}
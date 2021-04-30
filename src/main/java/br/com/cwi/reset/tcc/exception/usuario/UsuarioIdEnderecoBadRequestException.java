package br.com.cwi.reset.tcc.exception.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioIdEnderecoBadRequestException extends RuntimeException{

    public UsuarioIdEnderecoBadRequestException() {
        super("Usuario sem Endereços cadastrados!");
    }
}
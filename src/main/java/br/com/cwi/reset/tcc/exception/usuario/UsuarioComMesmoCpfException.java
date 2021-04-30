package br.com.cwi.reset.tcc.exception.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioComMesmoCpfException extends RuntimeException{

    public UsuarioComMesmoCpfException() {
        super("Nao e possivel cadastrar um usuario com o mesmo CPF!");
    }
}
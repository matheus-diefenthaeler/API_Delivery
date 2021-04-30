package br.com.cwi.reset.tcc.exception.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsuarioComMesmoEmailException extends RuntimeException{

    public UsuarioComMesmoEmailException() {
        super("Nao e possivel cadastrar um usuario com o mesmo email!");
    }
}

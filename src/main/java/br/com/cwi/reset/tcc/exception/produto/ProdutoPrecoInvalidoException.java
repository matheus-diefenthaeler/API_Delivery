package br.com.cwi.reset.tcc.exception.produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProdutoPrecoInvalidoException extends RuntimeException{

    public ProdutoPrecoInvalidoException() {
        super("O valor do produto precisa ser acima de R$0.00 !");
    }
}

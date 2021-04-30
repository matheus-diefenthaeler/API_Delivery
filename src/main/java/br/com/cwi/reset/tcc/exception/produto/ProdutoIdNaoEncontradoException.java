package br.com.cwi.reset.tcc.exception.produto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProdutoIdNaoEncontradoException extends RuntimeException{

    public ProdutoIdNaoEncontradoException() {
        super("Produto nao encontrado, por favor informe um produto cadastrado no sistema.");
    }
}

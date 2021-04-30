package br.com.cwi.reset.tcc.exception.estabelecimento;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstabelecimentoIdEnderecoNaoEncontradoException extends RuntimeException{

    public EstabelecimentoIdEnderecoNaoEncontradoException() {
        super("ID Endereco do estabelecimento nao encontrado, por favor, informe um ID cadastrado.");
    }
}

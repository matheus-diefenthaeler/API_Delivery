package br.com.cwi.reset.tcc.exception.estabelecimento;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EstabelecimentoNaoAssociadoAoEnderecoException extends RuntimeException {

    public EstabelecimentoNaoAssociadoAoEnderecoException() {
        super("Id do Estabelecimento nao corresponde ao ID endereco!");
    }

}

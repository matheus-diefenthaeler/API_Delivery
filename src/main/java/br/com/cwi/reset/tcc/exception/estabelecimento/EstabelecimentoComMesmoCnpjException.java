package br.com.cwi.reset.tcc.exception.estabelecimento;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EstabelecimentoComMesmoCnpjException extends RuntimeException {

    public EstabelecimentoComMesmoCnpjException() {
        super("Nao e possivel cadastrar um Estabelecimento com um CNPJ existente no sistema!");
    }

}

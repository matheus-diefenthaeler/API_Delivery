package br.com.cwi.reset.tcc.dominio;

public enum FormaPagamento {

    CARTAO_CREDITO("Cartão de Crédito"),
    CARTAO_DEBITO("Cartão de Débito"),
    VALE_REFEICAO("Vale Refeição"),
    PIX("Pix"),
    DINHEIRO("Dinheiro");

    private String descricao;

    FormaPagamento(String descricao) {
        this.descricao = descricao;
    }
}

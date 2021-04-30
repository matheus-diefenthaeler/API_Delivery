package br.com.cwi.reset.tcc.response.produto;

import br.com.cwi.reset.tcc.dominio.Categoria;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProdutoResponse {

    private final Long idProduto;
    private final String titulo;
    private final String descricao;
    private final BigDecimal valor;
    private final String cnpj;
    private final Categoria categoria;
    private final Integer tempoPreparo;
    private final String urlFoto;

    public ProdutoResponse(Long idProduto, String titulo, String descricao, BigDecimal valor, String cnpj, Categoria categoria, Integer tempoPreparo, String urlFoto) {
        this.idProduto = idProduto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.valor = valor;
        this.cnpj = cnpj;
        this.categoria = categoria;
        this.tempoPreparo = tempoPreparo;
        this.urlFoto = urlFoto;
    }
}

package br.com.cwi.reset.tcc.request.produto;

import br.com.cwi.reset.tcc.dominio.Categoria;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequest {

    private String titulo;
    private String descricao;
    private BigDecimal valor;
    private String urlFoto;
    private Categoria categoria;
    private Integer tempoPreparo;
    private Long idEstabelecimento;

}

package br.com.cwi.reset.tcc.service.pedido;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.dominio.ItemPedido;
import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.Produto;
import br.com.cwi.reset.tcc.mapper.pedido.PedidoItemMapper;
import br.com.cwi.reset.tcc.mapper.pedido.PedidoMapper;
import br.com.cwi.reset.tcc.mapper.pedido.PedidoResponseMapper;
import br.com.cwi.reset.tcc.repository.ItemPedidoRepository;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import br.com.cwi.reset.tcc.repository.ProdutoRepository;
import br.com.cwi.reset.tcc.request.pedido.ItemPedidoRequest;
import br.com.cwi.reset.tcc.request.pedido.PedidoRequest;
import br.com.cwi.reset.tcc.response.pedido.PedidoResponse;
import br.com.cwi.reset.tcc.service.enderecos.EnderecosService;
import br.com.cwi.reset.tcc.service.estabelecimento.EstabelecimentoService;
import br.com.cwi.reset.tcc.service.produto.ProdutoService;
import br.com.cwi.reset.tcc.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FazerPedidoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @Autowired
    private EnderecosService enderecosService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    public PedidoResponse fazerPedido(PedidoRequest pedidoRequest) {
        pedidoService.checkProdutoCampos(pedidoRequest);
        Estabelecimento estabelecimento = estabelecimentoService.verificaIdEstabelecimento(pedidoRequest);
        estabelecimentoService.checkHorarioFuncionamento(estabelecimento);

        List<ItemPedido> listaDeItensPedidos = new ArrayList<>();
        Integer tempo = 0;
        Integer quantidade = 0;
        double valorTotalDoPedido = 0.0;
        int tempoTotal=0;

        for(ItemPedidoRequest itemPedidoRequest : pedidoRequest.getItens()) {

            pedidoService.checkQuantidade(itemPedidoRequest.getQuantidade());

            Optional<Produto> produto = produtoRepository.findById(itemPedidoRequest.getIdProduto());
            produtoService.validaProdutoId(itemPedidoRequest.getIdProduto());
            if(produto.isPresent()){
                if(!produto.get().getEstabelecimento().getId().equals(estabelecimento.getId())){
                    throw new RuntimeException("Produto nao encontrado para esse estabelecimento");
                }
                listaDeItensPedidos.add(PedidoItemMapper.toItemPedido(produto.get(), itemPedidoRequest));
                tempo = produto.get().getTempoPreparo();
                quantidade = itemPedidoRequest.getQuantidade();
                valorTotalDoPedido += produto.get().getValor().doubleValue() * itemPedidoRequest.getQuantidade();
                tempoTotal +=  pedidoService.tempoEstimadoEmMinutos(tempo,quantidade);
            }
        }

        Pedido fazerPedido = PedidoMapper.toPedido(pedidoRequest,listaDeItensPedidos);
        fazerPedido.setSolicitante(usuarioService.findUsuarioComEnderecoById(pedidoRequest.getIdUsuarioSolicitante()));
        fazerPedido.setEstabelecimento(estabelecimento);
        fazerPedido.setEnderecoEntrega(enderecosService.verificaEnderecoUsuario(pedidoRequest));
        fazerPedido.setValorTotal(new BigDecimal(valorTotalDoPedido).setScale(2,RoundingMode.HALF_EVEN));

        PedidoResponse response = PedidoResponseMapper.toPedidoResponse(pedidoRepository.save(fazerPedido));
        response.setTempoEstimadoEmMinutos(tempoTotal);
        return response;
    }
}

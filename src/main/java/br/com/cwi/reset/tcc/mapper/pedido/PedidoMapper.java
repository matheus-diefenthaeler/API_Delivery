package br.com.cwi.reset.tcc.mapper.pedido;

import br.com.cwi.reset.tcc.dominio.ItemPedido;
import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.repository.EnderecoRepository;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import br.com.cwi.reset.tcc.request.pedido.PedidoRequest;
import br.com.cwi.reset.tcc.service.enderecos.EnderecosService;
import br.com.cwi.reset.tcc.service.estabelecimento.ListarEstabelecimentoService;
import br.com.cwi.reset.tcc.service.usuario.ListarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoMapper {

    public static Pedido toPedido(PedidoRequest pedidoRequest, List<ItemPedido> listaItemPedido){

        Pedido pedido = new Pedido();

        pedido.setItensPedido(listaItemPedido);
        pedido.setFormaPagamento(pedidoRequest.getFormaPagamento());
        pedido.setHorarioSolicitacao(LocalDateTime.now());
        pedido.setStatus(StatusPedido.EM_PREPARO);

        return pedido;
    }
}

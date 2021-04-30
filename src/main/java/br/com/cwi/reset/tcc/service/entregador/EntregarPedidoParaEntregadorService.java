package br.com.cwi.reset.tcc.service.entregador;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.mapper.entregador.EntregadorResponseMapper;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import br.com.cwi.reset.tcc.response.entregador.EntregadorResponse;
import br.com.cwi.reset.tcc.service.pedido.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EntregarPedidoParaEntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private EntregadorService entregadorService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    public EntregadorResponse entregarPedidoParaEntregador(Long idPedido){

        Pedido pedido = pedidoService.findPedidoById(idPedido);
        pedidoService.verificaStatusEmPreparo(idPedido);

        pedido.setEntregador(entregadorService.findEntregadorDisponivel());
        Entregador entregador = pedido.getEntregador();
        entregador.setDisponivel(false);
        pedido.setHorarioSaiuParaEntrega(LocalDateTime.now());
        pedido.setStatus(StatusPedido.SAIU_PARA_ENTREGA);
        pedidoRepository.save(pedido);
        entregadorRepository.save(entregador);

        return EntregadorResponseMapper.toEntregadorResponse(entregador);
    }
}

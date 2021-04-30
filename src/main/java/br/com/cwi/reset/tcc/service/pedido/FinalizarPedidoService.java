package br.com.cwi.reset.tcc.service.pedido;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import br.com.cwi.reset.tcc.service.entregador.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FinalizarPedidoService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private EntregadorService entregadorService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void finalizarPedido(Long idPedido){

        Pedido pedido = pedidoService.findPedidoById(idPedido);
        pedidoService.verificaStatusSaiuParaEntrega(idPedido);
        Entregador entregador = pedido.getEntregador();
        entregador.setDisponivel(true);
        pedido.setHorarioEntrega(LocalDateTime.now());
        pedido.setStatus(StatusPedido.ENTREGUE);
        pedidoRepository.save(pedido);
        entregadorRepository.save(entregador);
    }
}

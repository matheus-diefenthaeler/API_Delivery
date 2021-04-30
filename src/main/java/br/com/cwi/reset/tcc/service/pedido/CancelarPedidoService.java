package br.com.cwi.reset.tcc.service.pedido;

import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import br.com.cwi.reset.tcc.service.entregador.EntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CancelarPedidoService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private EntregadorService entregadorService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    public void cancelarPedido(Long idPedido) {

        Pedido pedido = pedidoService.findPedidoById(idPedido);
        pedidoService.verificaStatusParaCancelar(idPedido);
        pedidoService.verificaHorarioParaCancelar(idPedido);
        pedido.setHorarioCancelamento(LocalDateTime.now());
        pedido.setStatus(StatusPedido.CANCELADO);
        pedidoRepository.save(pedido);
    }
}

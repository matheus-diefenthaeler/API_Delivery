package br.com.cwi.reset.tcc.service.pedido;

import br.com.cwi.reset.tcc.dominio.ItemPedido;
import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.mapper.pedido.BuscarPedidoResponseMapper;
import br.com.cwi.reset.tcc.response.pedido.BuscarPedidoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BuscarPedidoService {

    @Autowired
    private PedidoService pedidoService;

    public BuscarPedidoResponse getPedidoById(Long idPedido) {

        Pedido pedido =  pedidoService.findPedidoById(idPedido);
        LocalDateTime tempoPrevistoDeEntrega = pedido.getHorarioSolicitacao().plusMinutes(tempoTotalDePreparo(pedido)) ;
        return BuscarPedidoResponseMapper.toBuscarPedidoResponse(pedido,tempoPrevistoDeEntrega);
    }

    public int tempoTotalDePreparo(Pedido pedido){

        int tempoTotalDePreparo = 0;

        for(ItemPedido itemPedido : pedido.getItensPedido()){
            Integer tempoPreparo = itemPedido.getProduto().getTempoPreparo();
            Integer quantidadeItem = itemPedido.getQuantidade();
            tempoTotalDePreparo += tempoPreparo*quantidadeItem;
        }
        return  tempoTotalDePreparo;
    }
}

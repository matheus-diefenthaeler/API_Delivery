package br.com.cwi.reset.tcc.service.pedido;

import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.dominio.StatusPedido;
import br.com.cwi.reset.tcc.exception.pedido.PedidoCancelarException;
import br.com.cwi.reset.tcc.exception.pedido.PedidoEmPreparoException;
import br.com.cwi.reset.tcc.exception.pedido.PedidoSaiuParaEntregaException;
import br.com.cwi.reset.tcc.exception.pedido.TempoExcedidoException;
import br.com.cwi.reset.tcc.repository.PedidoRepository;
import br.com.cwi.reset.tcc.request.pedido.PedidoRequest;
import br.com.cwi.reset.tcc.service.enderecos.EnderecosService;
import br.com.cwi.reset.tcc.service.estabelecimento.ListarEstabelecimentoService;
import br.com.cwi.reset.tcc.service.usuario.ListarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ListarEstabelecimentoService listarEstabelecimentoService;

    @Autowired
    private ListarUsuarioService listarUsuarioService;

    @Autowired
    private EnderecosService enderecosService;

    public Pedido findPedidoById(Long id){

        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(10);

        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(!pedido.isPresent()){
            throw new RuntimeException("Pedido nao econtrado");
        }
        return pedido.get();
    }

    public void verificaStatusEmPreparo(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(!pedido.get().getStatus().equals(StatusPedido.EM_PREPARO)){
            throw new PedidoEmPreparoException();
        }
    }

    public void verificaStatusParaCancelar(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(!pedido.get().getStatus().equals(StatusPedido.EM_PREPARO)){
            throw new PedidoCancelarException();
        }
    }

    public void verificaStatusSaiuParaEntrega(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (!pedido.get().getStatus().equals(StatusPedido.SAIU_PARA_ENTREGA)) {
            throw new PedidoSaiuParaEntregaException();
        }
    }

    public void verificaHorarioParaCancelar(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if(LocalDateTime.now().isAfter(pedido.get().getHorarioSolicitacao().plusMinutes(10))) {
            throw new TempoExcedidoException();
        }
    }

    public void checkQuantidade(Integer quantidade){
        if(quantidade >= 5){
            throw new RuntimeException("Nao é possivel solicitar mais do que 5 itens do mesmo produto em um pedido.");
        }
    }

    public Boolean isCamposPreenchidos(PedidoRequest fazerPedidoRequest){
        return fazerPedidoRequest.getIdEstabelecimento() != null && fazerPedidoRequest.getIdUsuarioSolicitante() != null
                && fazerPedidoRequest.getIdEnderecoEntrega() != null && fazerPedidoRequest.getItens() != null &&
                fazerPedidoRequest.getFormaPagamento() != null;
    }

    public void checkProdutoCampos(PedidoRequest pedidoRequest){
        if(!isCamposPreenchidos(pedidoRequest)){
            throw new RuntimeException("Preencha os campos obrigatórios");
        }
    }

    public Integer tempoEstimadoEmMinutos(Integer quantidade, Integer tempo){
        return quantidade*tempo;
    }

    public BigDecimal valorTotal(Integer quantidade, BigDecimal precoProduto){
        return new BigDecimal(quantidade*precoProduto.doubleValue());
    }

}


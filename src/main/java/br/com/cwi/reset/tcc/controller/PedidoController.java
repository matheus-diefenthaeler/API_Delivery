package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.request.pedido.PedidoRequest;
import br.com.cwi.reset.tcc.response.entregador.EntregadorResponse;
import br.com.cwi.reset.tcc.response.pedido.BuscarPedidoResponse;
import br.com.cwi.reset.tcc.response.pedido.PedidoResponse;
import br.com.cwi.reset.tcc.service.entregador.EntregarPedidoParaEntregadorService;
import br.com.cwi.reset.tcc.service.entregador.ListarEntregadorService;
import br.com.cwi.reset.tcc.service.pedido.BuscarPedidoService;
import br.com.cwi.reset.tcc.service.pedido.CancelarPedidoService;
import br.com.cwi.reset.tcc.service.pedido.FazerPedidoService;
import br.com.cwi.reset.tcc.service.pedido.FinalizarPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pedidos")

public class PedidoController {

    @Autowired
    private FazerPedidoService pedidoService;

    @Autowired
    private BuscarPedidoService buscarPedidoService;

    @Autowired
    private ListarEntregadorService listarEntregadorService;

    @Autowired
    private EntregarPedidoParaEntregadorService entregarPedidoParaEntregadorService;

    @Autowired
    private FinalizarPedidoService finalizarPedidoService;

    @Autowired
    private CancelarPedidoService cancelarPedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private PedidoResponse fazerPedido(@RequestBody PedidoRequest pedidoRequest){
        return pedidoService.fazerPedido(pedidoRequest);
    }

    @GetMapping("/{id}")
    public BuscarPedidoResponse getPedidoById(@PathVariable("id") final Long id) {
        return buscarPedidoService.getPedidoById(id);
    }

    @PutMapping("/{id}/entregar")
    public EntregadorResponse entregarPedidoParaEntregador(@PathVariable("id") final Long id){
        return entregarPedidoParaEntregadorService.entregarPedidoParaEntregador(id);
    }

    @PutMapping("/{id}/finalizar")
    public void finalizarPedido(@PathVariable("id") final Long id){
        finalizarPedidoService.finalizarPedido(id);
    }

    @DeleteMapping("/{id}")
    public void deletarEndereco(@PathVariable("id") final @Valid Long id){
        cancelarPedidoService.cancelarPedido(id);
    }

}

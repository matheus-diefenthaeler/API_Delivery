package br.com.cwi.reset.tcc.mapper.pedido;

import br.com.cwi.reset.tcc.dominio.Pedido;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import br.com.cwi.reset.tcc.response.pedido.PedidoResponse;
import br.com.cwi.reset.tcc.service.usuario.ListarUsuarioService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public class PedidoResponseMapper {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ListarUsuarioService listarUsuarioService;

    public static PedidoResponse toPedidoResponse(Pedido pedido){

        return new PedidoResponse(pedido.getId(), pedido.getValorTotal(), pedido.getStatus(),pedido.getEnderecoEntrega());

    }
}

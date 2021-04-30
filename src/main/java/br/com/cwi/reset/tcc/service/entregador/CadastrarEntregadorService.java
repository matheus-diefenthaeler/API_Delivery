package br.com.cwi.reset.tcc.service.entregador;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired EntregadorService entregadorService;

    public Entregador salvarEntregador (Entregador entregador){

        entregadorService.validaUsuario(entregador);

        return entregadorRepository.save(entregador);
    }
}

package br.com.cwi.reset.tcc.service.entregador;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.exception.entregador.EntregadorComMesmoCpfException;
import br.com.cwi.reset.tcc.exception.entregador.EntregadorNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntregadorService {

    @Autowired
    EntregadorRepository entregadorRepository;

    public Entregador getEntregadorById(Long id) {

        Optional<Entregador> entregador = entregadorRepository.findById(id);
        if(!entregador.isPresent()){
            throw new EntregadorNaoEncontradoException();
        }
        return entregador.get();
    }

    public Entregador findEntregadorDisponivel(){

        List<Entregador> entregadoresDisponiveis = new ArrayList<>();

        for(Entregador entregador : entregadorRepository.findAll()){
            if(entregador.getDisponivel()){
                entregadoresDisponiveis.add(entregador);
            }
        }
        if(entregadoresDisponiveis.isEmpty()){
            throw new RuntimeException("No momento não temos nenhum entregador disponivel, por favor, tente novamente mais tarde!");
        }
        return entregadoresDisponiveis.get(0);
    }

    public void validaUsuario(Entregador entregador) {

        if(entregadorRepository.existsByCpf(entregador.getCpf())) {
            throw new EntregadorComMesmoCpfException();
        }
    }
}

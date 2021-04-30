package br.com.cwi.reset.tcc.service.estabelecimento;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.dominio.HorarioFuncionamento;
import br.com.cwi.reset.tcc.exception.estabelecimento.EstabelecimentoComMesmoCnpjException;
import br.com.cwi.reset.tcc.exception.estabelecimento.EstabelecimentoIdNaoEncontradoException;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import br.com.cwi.reset.tcc.request.pedido.PedidoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    public Estabelecimento findEstabelecimentoById(Long id){
        Optional<Estabelecimento> estabelecimento = estabelecimentoRepository.findById(id);
        if(!estabelecimento.isPresent()){
            throw new EstabelecimentoIdNaoEncontradoException();
        }
        return estabelecimento.get();
    }

    private boolean isValidHorarioDeFuncionamento(Estabelecimento estabelecimento) {
        LocalDateTime dataSolicitao = LocalDateTime.now();
        for(HorarioFuncionamento horarioFuncionamento : estabelecimento.getHorariosFuncionamento() ){
            if(dataSolicitao.getDayOfWeek().equals(horarioFuncionamento.getDiaSemana())){
                if(horarioFuncionamento.getHorarioAbertura().isBefore(dataSolicitao.toLocalTime()) &&
                        horarioFuncionamento.getHorarioFechamento().isAfter(dataSolicitao.toLocalTime())){
                    return true;
                }
            }
        }
        return false;
    }

    public void checkHorarioFuncionamento(Estabelecimento estabelecimento){
        if(!isValidHorarioDeFuncionamento(estabelecimento)){
            throw new RuntimeException("Estabelecimento fechado.");
        }
    }

    public Estabelecimento verificaIdEstabelecimento(PedidoRequest pedidoRequest) {
        return findEstabelecimentoById(pedidoRequest.getIdEstabelecimento());
    }

    public void validaEstabelecimentoByCnpj(Estabelecimento estabelecimento) {
        if(estabelecimentoRepository.existsByCnpj(estabelecimento.getCnpj())){
            throw new EstabelecimentoComMesmoCnpjException();
        }
    }
}

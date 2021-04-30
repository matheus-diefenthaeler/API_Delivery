package br.com.cwi.reset.tcc.service.estabelecimento;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastrarEstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    public Estabelecimento salvarEstabelecimento (Estabelecimento estabelecimento){

        estabelecimentoService.validaEstabelecimentoByCnpj(estabelecimento);

        return estabelecimentoRepository.save(estabelecimento);
    }
}

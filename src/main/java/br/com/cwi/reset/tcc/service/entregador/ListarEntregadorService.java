package br.com.cwi.reset.tcc.service.entregador;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.repository.EntregadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListarEntregadorService {

    @Autowired
    private EntregadorRepository entregadorRepository;

    public Page<Entregador> getEntregador(Pageable pageable) {
        Sort sort = pageable.getSortOr(Sort.by(Sort.Direction.ASC,"nome"));
        return entregadorRepository.findAll(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),sort));
    }
}

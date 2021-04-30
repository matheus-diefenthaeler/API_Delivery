package br.com.cwi.reset.tcc.service.estabelecimento;

import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.repository.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListarEstabelecimentoService {

    @Autowired
    private EstabelecimentoRepository estabelecimentoRepository;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    // ************************ Explicacao do Metodo getUsuario utilizando paginacao e ordenacao ************************
   /* A funcao getEstabelecimento recebe do Postman um pageable. Caso nao seja informado alguma ordenacao, o Sort
     vai por padrao ordernar por Nome ASC.
     O findAll entao recebe um novo pageable com ordenacao padrao ou a que veio do Postman.
     */
    public Page<Estabelecimento> getEstabelecimentos(Pageable pageable){
        Sort sort = pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nomeFantasia"));
        return estabelecimentoRepository.findAll(PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort));
    }
}

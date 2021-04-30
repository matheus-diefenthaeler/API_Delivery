package br.com.cwi.reset.tcc.service.usuario;

import br.com.cwi.reset.tcc.dominio.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ListarUsuarioService {

    @Autowired
    private UsuarioService usuarioService;

    // ************************ Explicacao do Metodo getUsuario utilizando paginacao e ordenacao ************************
   /* A funcao getUsuario recebe do Postman um pageable. Caso nao seja informado alguma ordenacao, o Sort
     vai por padrao ordernar por Nome ASC.
     O findAll entao recebe um novo pageable com ordenacao padrao ou a que veio do Postman.
     */

    public Page<Usuario> findUsuarioByPageable(Pageable pageable) {
        Sort sort = pageable.getSortOr(Sort.by(Sort.Direction.ASC, "nome"));
        return usuarioService.findUsuarioByPageable(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort));
    }
}

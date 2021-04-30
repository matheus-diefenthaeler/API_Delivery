package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Entregador;
import br.com.cwi.reset.tcc.service.entregador.CadastrarEntregadorService;
import br.com.cwi.reset.tcc.service.entregador.ListarEntregadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    @Autowired
    private CadastrarEntregadorService cadastrarEntregadorService;

    @Autowired
    private ListarEntregadorService listarEntregadorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entregador salvarEntregador(@RequestBody @Valid Entregador entregador) {
        return cadastrarEntregadorService.salvarEntregador(entregador);
    }

    @GetMapping
    public Page<Entregador> getEntregadores(Pageable pageable){
        return listarEntregadorService.getEntregador(pageable);
    }

}

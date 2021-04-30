package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Estabelecimento;
import br.com.cwi.reset.tcc.service.estabelecimento.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    @Autowired
    private CadastrarEstabelecimentoService cadastrarEstabelecimentoService;

    @Autowired
    private ListarEstabelecimentoService listarEstabelecimentoService;

    @Autowired
    private DeletarEnderecoEstabelecimentoService deletarEnderecoEstabelecimentoService;

    @Autowired
    private CadastrarEnderecoEstabelecimentoService cadastrarEnderecoEstabelecimentoService;

    @Autowired
    private EstabelecimentoService estabelecimentoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estabelecimento salvarEstabelecimento(@RequestBody @Valid Estabelecimento estabelecimento){
        return cadastrarEstabelecimentoService.salvarEstabelecimento(estabelecimento);
    }

    @GetMapping
    public Page<Estabelecimento> getEstabelecimentos(Pageable pageable){
        return listarEstabelecimentoService.getEstabelecimentos(pageable);
    }

    @GetMapping("/{id}")
    public Estabelecimento findEstabelecimentoById(@PathVariable("id") final Long id) {
        return estabelecimentoService.findEstabelecimentoById(id);
    }

    @DeleteMapping("/{id}/enderecos/{idEndereco}")
    public void deletarEndereco(@PathVariable("id") final @Valid Long id,
                                @PathVariable("idEndereco") final @Valid Long idEndereco) {
        deletarEnderecoEstabelecimentoService.deletarEstabelecimentoEndereco(id, idEndereco);
    }

    @PostMapping("/{id}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco cadastrarEnderecoEstabelecimento(@PathVariable("id") final Long id, @RequestBody @Valid Endereco endereco) {
        return cadastrarEnderecoEstabelecimentoService.salvarEnderecoEstabelecimento(endereco, id);
    }
}

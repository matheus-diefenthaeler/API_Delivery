package br.com.cwi.reset.tcc.controller;

import br.com.cwi.reset.tcc.dominio.Endereco;
import br.com.cwi.reset.tcc.dominio.Usuario;
import br.com.cwi.reset.tcc.repository.UsuarioRepository;
import br.com.cwi.reset.tcc.service.usuario.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {


    @Autowired
    private ListarUsuarioService listarUsuarioService;

    @Autowired
    private CadastrarEnderecoUsuarioService cadastrarEnderecoUsuarioService;

    @Autowired
    private DeletarEnderecoUsuarioService deletarEnderecoUsuarioService;

    @Autowired
    private AlterarUsuarioService alterarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @GetMapping
    public Page<Usuario> findUsuarioByPageable(Pageable pageable) {
        return listarUsuarioService.findUsuarioByPageable(pageable);
    }

    @GetMapping("/{id}")
    public Usuario findUsuarioById(@PathVariable("id") final Long id) {
        return usuarioService.findUsuarioById(id);
    }

    @PostMapping("/{id}/enderecos")
    @ResponseStatus(HttpStatus.CREATED)
    public Endereco cadastrarEndereco(@PathVariable("id") final Long id, @RequestBody @Valid Endereco endereco) {
        return cadastrarEnderecoUsuarioService.salvarEndereco(endereco, id);
    }

    @DeleteMapping("/{id}/enderecos/{idEndereco}")
    public void deletarEndereco(@PathVariable("id") final @Valid Long id,
                                @PathVariable("idEndereco") final @Valid Long idEndereco) {
        deletarEnderecoUsuarioService.deletarEndereco(id, idEndereco);
    }

    @PutMapping("/{id}")
    public Usuario alterarUsuario(@RequestBody Usuario novoUsuario, @PathVariable @Valid Long id){
        return alterarUsuarioService.alterarUsuario(novoUsuario, id);
    }
}

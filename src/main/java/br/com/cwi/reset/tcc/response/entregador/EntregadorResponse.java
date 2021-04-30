package br.com.cwi.reset.tcc.response.entregador;

import lombok.Getter;

@Getter
public class EntregadorResponse {

    private final Long id;
    private final String cpf;
    private final String nome;
    private final String telefone;
    private final String placaVeiculo;

    public EntregadorResponse(Long id, String cpf, String nome, String telefone, String placaVeiculo) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.placaVeiculo = placaVeiculo;
    }
}

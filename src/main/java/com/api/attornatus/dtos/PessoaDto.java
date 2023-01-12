package com.api.attornatus.dtos;

import jakarta.validation.constraints.NotBlank;

public class PessoaDto {

    @NotBlank
    private String nome;

    @NotBlank
    private String nascimento;

    @NotBlank
    private String endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}

package com.projeto.urbana.dto;

import com.projeto.urbana.tipos.TipoCartao;

public class CartaoCadastroDTO {
    private String numeroCartao;
    private String nome;
    private TipoCartao tipo;

    public String getNumeroCartao() { return numeroCartao; }
    public void setNumeroCartao(String numeroCartao) { this.numeroCartao = numeroCartao; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public TipoCartao getTipo() { return tipo; }
    public void setTipo(TipoCartao tipo) { this.tipo = tipo; }
}

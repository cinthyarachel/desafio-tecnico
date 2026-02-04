package com.projeto.urbana.dto;

import com.projeto.urbana.tipos.StatusCartao;
import com.projeto.urbana.tipos.TipoCartao;

public class CartaoRetornoDTO {
    private Long id;
    private String numeroCartao;
    private String nome;
    private TipoCartao tipo;
    private StatusCartao status;

    public CartaoRetornoDTO(Long id, String numeroCartao, String nome, TipoCartao tipo, StatusCartao status) {
        this.id = id;
        this.numeroCartao = numeroCartao;
        this.nome = nome;
        this.tipo = tipo;
        this.status = status;
    }

    public Long getId() { return id; }
    public String getNumeroCartao() { return numeroCartao; }
    public String getNome() { return nome; }
    public TipoCartao getTipo() { return tipo; }
    public StatusCartao getStatus() { return status; }
}

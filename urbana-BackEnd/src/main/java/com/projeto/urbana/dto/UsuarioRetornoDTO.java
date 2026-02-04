package com.projeto.urbana.dto;

import java.util.List;

public class UsuarioRetornoDTO {
    private Long id;
    private String nome;
    private String email;
    private List<CartaoRetornoDTO> cartoes;

    public UsuarioRetornoDTO(Long id, String nome, String email, List<CartaoRetornoDTO> cartoes) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cartoes = cartoes;
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public List<CartaoRetornoDTO> getCartoes() { return cartoes; }
}
